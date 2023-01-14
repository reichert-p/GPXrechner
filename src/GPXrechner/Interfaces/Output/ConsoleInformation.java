package GPXrechner.Interfaces.Output;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Calculations.MovementSpeed.Sport;
import GPXrechner.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Calculations.TourSplitting.WayPointSet;
import GPXrechner.WayModel.Location;
import GPXrechner.WayModel.Profiles.ElevationProfile;
import GPXrechner.WayModel.Profiles.SpeedProfile;

import java.time.Duration;

public class ConsoleInformation{
    public static void userToGiveClassPath(String basePath){
        userToGiveClassPath(basePath,null);
    }

    public static void userToGiveClassPath(String basePath, String abortOption){
        if (abortOption == null)
            System.out.println("Please provide file path relative to '" + basePath + "'");
        else
            System.out.println("Please provide file path relative to '" + basePath + "'. To abort press: " + abortOption);
    }

    public static void userToProvideInstruction(){
        System.out.println("Please enter instruction");
    }

    public static void userToProvideGranularity(){
        System.out.println("Please provide Granularity of x-Axis");
    }

    public static void userToProvideMovementSpeed(){
        System.out.println("Please provide Sport. Alternatively write 'PMS' for parsing personal movement Speed");
    }

    public static void provideInstructions(Instruction[] instructions){ //das vielleicht in help Instruction?
        System.out.println("Instructions are");
        for (Instruction i: instructions) {
            System.out.println(i.getDescription() + " (" + i.getRegex() + ")");
        }
    }

    public static void provideSports(){
        System.out.println("Available Sports are");
        for (Sport sport:Sport.values()) {
            System.out.println(sport.name().toLowerCase());
        }
    }

    public static void alertWrongFileType(String filePath, String expected){
        System.out.println(filePath + " is not of type " + expected);
    }

    public static void alertGranularityTooHigh(int granularity){
        System.out.println("granularity " + granularity + " too high for amount of Locations in Path");
    }

    public static void showElevationProfile(String tourName, ElevationProfile elevationProfile){
        System.out.println("Elevation Profile of Tour " + tourName + ":");
        printBoolMatrix(elevationProfile.getProfile());
    }
    public static void showSpeedProfile(String tourName, SpeedProfile speedProfile){
        System.out.println("Speed Profile of Tour " + tourName + ":");
        printBoolMatrix(speedProfile.getProfile());
    }

    public static void printBoolMatrix(boolean[][] matrix){
        int xLength = matrix[0].length;
        int yLength = matrix.length;
        for (int i = xLength - 1; i > -1 ; i-- ){
            for (int j = 0; j < yLength; j ++){
                if (matrix[j][i]){
                    System.out.print("^");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void infoEstimatedTime(String pathName, Duration estimatedTime){
        System.out.println("Estimated Time for Path " + pathName + ": " + estimatedTime);

    }
    public static void infoPMSofTour(String tourName, MovementSpeed pms){
        System.out.println("PMS of Tour " + tourName + ":");
        infoPMS(pms);
    }
    public static void infoPMS(MovementSpeed pms){
        System.out.println("Horizontal speed:" + pms.getHorizontalSpeed());
        System.out.println("Climbing speed  :" + pms.getClimbingSpeed());
        System.out.println("Descending speed:" + pms.getDescendingSpeed());
    }
    public static void infoTimeTaken(String tourName, Duration timeTaken){
        System.out.println("Time taken for Tour " + tourName + timeTaken.toString());
    }
    public static void infoPathElevationGain(String pathname, String elevationGain){
        System.out.println("Elevation difference of Path " + pathname + ": " + elevationGain);
    }
    public static void infoPathLength(String pathName, String length){
        System.out.printf("Length of Path " + pathName + ": " + length);
    }
    public static void infoGeneratedTrack(String trackName){
        System.out.println("Generated Track " + trackName);
    }

    public static void infoWayPointsToSplit(WayPointSet splitPoints) {
        System.out.println("Proposition to split Path at: ");
        for (Location s: splitPoints.getWayPoints()) {
            System.out.println(s.toString());
        }
    }

    public static void info(String s) {
        System.out.println(s);
    }

    public static void provideEvaluationFunctions(EvaluationFunction[] evaluationFunctions) {
        System.out.println("Supported Evaluation Functions are: ");
        for (EvaluationFunction i: evaluationFunctions) {
            System.out.println(i.getDescription() + " (" + i.getRegex() + ")");
        }
    }
}
