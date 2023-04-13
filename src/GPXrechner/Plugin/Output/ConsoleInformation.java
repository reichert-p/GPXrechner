package GPXrechner.Plugin.Output;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Application.UserOutput;
import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.MovementSpeed.Sport;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Domain.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Domain.WayModel.WayModel.Location;
import GPXrechner.Domain.WayModel.WayModel.Profiles.ElevationProfile;
import GPXrechner.Domain.WayModel.WayModel.Profiles.SpeedProfile;

import java.time.Duration;

public class ConsoleInformation implements UserOutput {
    public void userToGiveClassPath(String basePath) {
        userToGiveClassPath(basePath, null);
    }

    public void userToGiveClassPath(String basePath, String abortOption) {
        if (abortOption == null)
            System.out.println("Please provide file path relative to '" + basePath + "'");
        else
            System.out.println("Please provide file path relative to '" + basePath + "'. To abort press: " + abortOption);
    }

    public void userToProvideInstruction() {
        System.out.println("Please enter instruction");
    }

    public void userToProvideGranularity() {
        System.out.println("Please provide Granularity of x-Axis");
    }

    public void userToProvideMovementSpeed() {
        System.out.println("Please provide Sport. Alternatively write 'PMS' for parsing personal movement Speed");
    }

    public void provideInstructions(Instruction[] instructions) { //das vielleicht in help Instruction?
        System.out.println("Instructions are");
        for (Instruction i : instructions) {
            System.out.println(i.getDescription() + " (" + i.getRegex() + ")");
        }
    }

    public void provideSports() {
        System.out.println("Available Sports are");
        for (Sport sport : Sport.values()) {
            System.out.println(sport.name().toLowerCase());
        }
    }

    public void alertWrongFileType(String filePath, String expected) {
        System.out.println(filePath + " is not of type " + expected);
    }

    public void alertGranularityTooHigh(int granularity) {
        System.out.println("granularity " + granularity + " too high for amount of Locations in Path");
    }

    public void showElevationProfile(String tourName, ElevationProfile elevationProfile) {
        System.out.println("Elevation Profile of Tour " + tourName + ":");
        printBoolMatrix(elevationProfile.getProfile());
    }

    public void showSpeedProfile(String tourName, SpeedProfile speedProfile) {
        System.out.println("Speed Profile of Tour " + tourName + ":");
        printBoolMatrix(speedProfile.getProfile());
    }

    public void printBoolMatrix(boolean[][] matrix) {
        int xLength = matrix[0].length;
        int yLength = matrix.length;
        for (int i = xLength - 1; i > -1; i--) {
            for (int j = 0; j < yLength; j++) {
                if (matrix[j][i]) {
                    System.out.print("^");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void infoEstimatedTime(String pathName, Duration estimatedTime) {
        System.out.println("Estimated Time for Path " + pathName + ": " + estimatedTime);

    }

    public void infoPMSofTour(String tourName, MovementSpeed pms) {
        System.out.println("PMS of Tour " + tourName + ":");
        infoPMS(pms);
    }

    public void infoPMS(MovementSpeed pms) {
        System.out.println("Horizontal speed:" + pms.getHorizontalSpeed());
        System.out.println("Climbing speed  :" + pms.getClimbingSpeed());
        System.out.println("Descending speed:" + pms.getDescendingSpeed());
    }

    public void infoTimeTaken(String tourName, Duration timeTaken) {
        System.out.println("Time taken for Tour " + tourName + timeTaken.toString());
    }

    public void infoPathElevationGain(String pathname, String elevationGain) {
        System.out.println("Elevation difference of Path " + pathname + ": " + elevationGain);
    }

    public void infoPathLength(String pathName, String length) {
        System.out.printf("Length of Path " + pathName + ": " + length);
    }

    public void infoGeneratedTrack(String trackName) {
        System.out.println("Generated Track " + trackName);
    }

    public void infoWayPointsToSplit(WayPointSet splitPoints) {
        System.out.println("Proposition to split Path at: ");
        for (Location s : splitPoints.getWayPoints()) {
            System.out.println(s.toString());
        }
    }

    public void info(String s) {
        System.out.println(s);
    }

    public void provideEvaluationFunctions(EvaluationFunction[] evaluationFunctions) {
        System.out.println("Supported Evaluation Functions are: ");
        for (EvaluationFunction i : evaluationFunctions) {
            System.out.println(i.getDescription() + " (" + i.getRegex() + ")");
        }
    }
}
