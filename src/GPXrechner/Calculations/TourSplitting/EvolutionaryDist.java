package GPXrechner.Calculations.TourSplitting;

import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TimePrediction;
import GPXrechner.Inputhandling.Parsing.DOMParser;
import GPXrechner.Inputhandling.Parsing.NoTrackException;
import GPXrechner.WayModel.Entities.Path;
import GPXrechner.WayModel.Entities.Track;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.WayPoint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EvolutionaryDist {

    final int generationSize = 10;
    ArrayList<Location> path;
    Detours detours;

    MovementSpeed movementSpeed;

    Representation[] representations = new Representation[generationSize];

    Duration maxTimeBetweenWaypoints;

    public static void main(String[] args) throws NoTrackException, NoWayPointsExeption {
        DOMParser domParser = new DOMParser();
        Track watzmannTrack = domParser.parseTrack("Files\\GPX\\Track\\Watzmann.gpx");
        WayPointSet wasser = domParser.parseWayPoints("Files\\GPX\\Waypoints\\WatzmannWasser.gpx");
        EvolutionaryDist evo = new EvolutionaryDist(watzmannTrack,wasser,Duration.ofHours(5), Sport.HIKING);

        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{true,true,true})));
        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{true,true,false})));
        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{true,false,true})));
        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{true,false,false})));
        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{false,true,true})));
        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{false,true,false})));
        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{false,false,true})));
        System.out.println(evo.evaluationFunction(new Representation(new boolean[]{false,false,false})));
    }

    public EvolutionaryDist(Path path, WayPointSet wayPointSet, Duration maxTimeBetweenWaypoints, MovementSpeed movementSpeed){
        this.path = path.getOrderedLocations();
        this.movementSpeed = movementSpeed;
        this.maxTimeBetweenWaypoints = maxTimeBetweenWaypoints;
        initDetours(path,wayPointSet);
        initRepresentations();
        EvolutionStep();
    }

    private void EvolutionStep() {
        int roundsWithoutImprovement;
        for (roundsWithoutImprovement = 0; roundsWithoutImprovement < 11; roundsWithoutImprovement++){
            long evaluationOld = evaluateGeneration(representations);
            Representation[] children = new Representation[15];
            for (int i = 0; i < children.length; i++) {
                children[i] = randomRecombination();
            }
            for (int i = 0; i < children.length; i++) {
                children[i] = mutate(children[i]);
            }
            Representation[] newGeneration = greedySelection(representations, children);
            long evaluationNew = evaluateGeneration(newGeneration);
            if (evaluationNew < evaluationOld) {
                representations = newGeneration;
                roundsWithoutImprovement = 0;
            }
        }
    }

    private Representation[] greedySelection(Representation[] representations, Representation[] children) {
        Representation[] both = Stream.concat(Arrays.stream(representations), Arrays.stream(children))
                .sorted(Comparator.comparing(e -> evaluationFunction(e))).collect(Collectors.toList()).toArray(new Representation[0]);
        return Arrays.copyOfRange(both,0,generationSize);
    }

    private Representation mutate(Representation child) {
        return child.bitFlip();
    }

    private Representation randomRecombination() {
        int index1 = (int)(Math.random() * generationSize);
        int index2 = (int)(Math.random() * generationSize);
        Representation parent1 = representations[index1];
        Representation parent2 = representations[index2];
        return parent1.crossover(parent2);
    }

    private long evaluateGeneration(Representation[] representations) {
        long sum = 0;
        for (Representation rep:representations) {
            sum += evaluationFunction(rep);
        }
        return sum;
    }

    public long evaluationFunction(Representation representation){ //TODO refactor name
        Duration sum = Duration.ZERO;
        Duration baseDuration = TimePrediction.predictTime(path, this.movementSpeed);
        for (int i = 0; i < detours.getPossibleDetours().size(); i++){
            if (representation.getBitstring()[i]){
                sum = sum.plus(detours.getPossibleDetours().get(i).expenditure);
            }
        }
        sum = sum.plus(baseDuration);
        double overShoot = getOvershoot(representation);
        if (overShoot > 1){
            overShoot -= 1;
            return (long)(sum.getSeconds() + sum.getSeconds() * 10 * overShoot);
        }
        return sum.getSeconds();
    }

    private double getOvershoot(Representation representation) {
        Duration max = Duration.ZERO;
        List<Detours.Detour> visitedDetours = new ArrayList<>();
        for (int i = 0; i < detours.getPossibleDetours().size();i++){
            if (representation.getBitstring()[i]){
                visitedDetours.add(detours.getPossibleDetours().get(i));
            }
        }
        List<Detours.Detour> orderedVisitedDetours = visitedDetours.stream().sorted(Comparator.comparing(Detours.Detour::getPosition)).collect(Collectors.toList());
        if(orderedVisitedDetours.isEmpty()){
            max = TimePrediction.predictTime(path, this.movementSpeed);
        }
        else {
            Duration firstSectionDuration = TimePrediction.predictTime(path.subList(0,orderedVisitedDetours.get(0).position),this.movementSpeed);
            firstSectionDuration = firstSectionDuration.plus(orderedVisitedDetours.get(0).expenditure);
            max = max(max, firstSectionDuration);
            for (int i = 0; i< orderedVisitedDetours.size()-1;i++){
                Duration sectionDuration = TimePrediction.predictTime(path.subList(orderedVisitedDetours.get(i).position,orderedVisitedDetours.get(i+1).position),Sport.HIKING);
                sectionDuration = sectionDuration.plus(orderedVisitedDetours.get(i).expenditure);
                sectionDuration = sectionDuration.plus(orderedVisitedDetours.get(i+1).expenditure);
                max = max(max,sectionDuration);
            }
            Duration lastSectionDuration = TimePrediction.predictTime(path.subList(orderedVisitedDetours.get(orderedVisitedDetours.size()-1).position, path.size()-1),this.movementSpeed);
            lastSectionDuration = lastSectionDuration.plus(orderedVisitedDetours.get(orderedVisitedDetours.size()-1).expenditure);
            max = max(max, lastSectionDuration);
        }
        return (double) (max.getSeconds())/(double) (maxTimeBetweenWaypoints.getSeconds());
    }

    private Duration max(Duration dur1, Duration dur2) {
        if (dur1.compareTo(dur2) >0 ){
            return dur1;
        }
        return dur2;
    }

    private void initRepresentations(){
        for (int i = 0; i < representations.length; i++){
            representations[i] = new Representation(detours.getPossibleDetours().size());
        }
    }
    private void initDetours(Path path,WayPointSet wayPointSet){
        detours = new Detours();
        for (Location wp: wayPointSet.getWayPoints()) {
            detours.addDetour(getDetour(path,(WayPoint) wp,new DirectWayHeuristic()));
        }
    }

    /**
     *
     * @param path  index of this path is the nearest point of path to location
     * @param location
     * @param th with help of this heuristic expense between two points is measured
     * @return index on path from which location should be accessed
     */
    private Detours.Detour getDetour(Path path, WayPoint location, TimeHeuristic th){
        ArrayList<Location> pathLocations = path.getOrderedLocations();
        int bestIndex = -1;
        Duration bestIndexDuration = Duration.ofDays(100);
        for (int i = 0; i < path.getOrderedLocations().size();i++){
            Duration durationSuggestion = th.predictTime(pathLocations.get(i),location, this.movementSpeed);
            if (durationSuggestion.compareTo(bestIndexDuration) < 0){
                bestIndexDuration = durationSuggestion;
                bestIndex = i;
            }
        }
        return new Detours.Detour(bestIndex,location,bestIndexDuration);
    }
}
