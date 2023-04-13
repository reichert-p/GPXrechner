package GPXrechner.Application;

import GPXrechner.Application.Instructions.Instruction;
import GPXrechner.Domain.Calculations.MovementSpeed.MovementSpeed;
import GPXrechner.Domain.Calculations.TourSplitting.Evaluation.EvaluationFunction;
import GPXrechner.Domain.Calculations.TourSplitting.WayPointSet;
import GPXrechner.Domain.WayModel.WayModel.Profiles.ElevationProfile;
import GPXrechner.Domain.WayModel.WayModel.Profiles.SpeedProfile;

import java.time.Duration;

public interface UserOutput {
    void userToGiveClassPath(String basePath);

    void userToGiveClassPath(String basePath, String abortOption);

    void userToProvideInstruction();

    void userToProvideGranularity();

    void userToProvideMovementSpeed();

    void provideInstructions(Instruction[] instructions);

    void provideSports();

    void alertWrongFileType(String filePath, String expected);

    void alertGranularityTooHigh(int granularity);

    void showElevationProfile(String tourName, ElevationProfile elevationProfile);

    void showSpeedProfile(String tourName, SpeedProfile speedProfile);

    void printBoolMatrix(boolean[][] matrix);

    void infoEstimatedTime(String pathName, Duration estimatedTime);

    void infoPMSofTour(String tourName, MovementSpeed pms);

    void infoPMS(MovementSpeed pms);

    void infoTimeTaken(String tourName, Duration timeTaken);

    void infoPathElevationGain(String pathname, String elevationGain);

    void infoPathLength(String pathName, String length);

    void infoGeneratedTrack(String trackName);

    void infoWayPointsToSplit(WayPointSet splitPoints);

    void info(String s);

    void provideEvaluationFunctions(EvaluationFunction[] evaluationFunctions);
}
