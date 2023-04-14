package GPXrechner.Domain.Calculations.TourSplitting;

import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Domain.WayModel.Entities.Path;
import GPXrechner.Domain.WayModel.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hillclimbing {

    final int generationSize = 10;
    String description;
    ArrayList<Location> path;
    Detours detours;
    MovementSpeed movementSpeed;
    EvaluationFunction evaluationFunction;
    Representation[] representations = new Representation[generationSize];

    public Hillclimbing(Path path, WayPointSet wayPointSet, MovementSpeed movementSpeed, EvaluationFunction evaluationFunction) {
        description = wayPointSet.description + " optimized for Path " + path.toString();
        this.path = path.getOrderedLocations();
        this.movementSpeed = movementSpeed;
        this.evaluationFunction = evaluationFunction;
        detours = Detours.initDetours(path, wayPointSet, new DirectWayHeuristic(movementSpeed));
        initRepresentations();

        this.hillClimbing();
    }


    public void hillClimbing() {
        int roundsWithoutImprovement;
        for (roundsWithoutImprovement = 0; roundsWithoutImprovement < 11; roundsWithoutImprovement++) {
            boolean improvement = EvolutionStep();
            if (improvement) {
                roundsWithoutImprovement = 0;
            }
        }
    }

    private boolean EvolutionStep() {
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
            return true;
        }
        return false;
    }

    private Representation[] greedySelection(Representation[] representations, Representation[] children) {
        Representation[] both = Stream.concat(Arrays.stream(representations), Arrays.stream(children))
                .sorted(Comparator.comparing(e -> evaluationFunction(e))).collect(Collectors.toList()).toArray(new Representation[0]);
        return Arrays.copyOfRange(both, 0, generationSize);
    }

    private Representation mutate(Representation child) {
        return child.bitFlip();
    }

    private Representation randomRecombination() {
        int index1 = (int) (Math.random() * generationSize);
        int index2 = (int) (Math.random() * generationSize);
        Representation parent1 = representations[index1];
        Representation parent2 = representations[index2];
        return parent1.crossover(parent2);
    }

    private long evaluateGeneration(Representation[] representations) {
        long sum = 0;
        for (Representation rep : representations) {
            sum += evaluationFunction(rep);
        }
        return sum;
    }

    public long evaluationFunction(Representation representation) {
        return evaluationFunction.evaluate(path, detours, representation);
    }

    private void initRepresentations() {
        for (int i = 0; i < representations.length; i++) {
            representations[i] = new Representation(detours.getPossibleDetours().size());
        }
    }

    public WayPointSet getBestRepresentation() {
        WayPointSet visitedWayPoints = new WayPointSet(description);
        boolean[] bestSolution = representations[0].getBitString();
        for (int i = 0; i < bestSolution.length; i++) {
            if (bestSolution[i]) {
                visitedWayPoints.addWayPoint(detours.possibleDetours.get(i).getDestination());
            }
        }
        return visitedWayPoints;
    }
}
