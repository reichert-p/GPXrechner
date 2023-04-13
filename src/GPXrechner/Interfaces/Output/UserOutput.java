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

public interface UserOutput {
    public void userToGiveClassPath(String basePath);
    public void userToGiveClassPath(String basePath, String abortOption);
    public void userToProvideInstruction();
    public void userToProvideGranularity();
    public void userToProvideMovementSpeed();
    public void provideInstructions(Instruction[] instructions);
    public void provideSports();
    public void alertWrongFileType(String filePath, String expected);
    public void alertGranularityTooHigh(int granularity);
    public void showElevationProfile(String tourName, ElevationProfile elevationProfile);
    public void showSpeedProfile(String tourName, SpeedProfile speedProfile);
    public void printBoolMatrix(boolean[][] matrix);
    public void infoEstimatedTime(String pathName, Duration estimatedTime);
    public void infoPMSofTour(String tourName, MovementSpeed pms);
    public void infoPMS(MovementSpeed pms);
    public void infoTimeTaken(String tourName, Duration timeTaken);
    public  void infoPathElevationGain(String pathname, String elevationGain);
    public void infoPathLength(String pathName, String length);
    public void infoGeneratedTrack(String trackName);
    public void infoWayPointsToSplit(WayPointSet splitPoints);
    public void info(String s);
    public void provideEvaluationFunctions(EvaluationFunction[] evaluationFunctions);
}
