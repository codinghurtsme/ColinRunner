package LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
public class Positioning {

    private static LLResult fetchResult(){
        return LimeLightBasics.validResult();
    }
    /**
     * Returns a 2D Position of the Robot based off of April-Tag Readings
     */
    protected static Pose2D getPositionInField() {
        LLResult result = fetchResult();

        Pose3D pose3D = result.getBotpose();
        if (pose3D == null) return null;

        Position position = pose3D.getPosition();
        YawPitchRollAngles angles = pose3D.getOrientation();

        double x = position.toUnit(DistanceUnit.INCH).x;
        double y = position.toUnit(DistanceUnit.INCH).y;
        double heading = angles.getYaw(AngleUnit.RADIANS);

        return new Pose2D(DistanceUnit.INCH, x, y, AngleUnit.RADIANS, heading);
    }
    /**
     * Returns a 2D Position of the Robot based off of April-Tag
     * readings, and if none are available, reruns fallback position
     */
    protected static Pose2D getPositionInField(Pose2D fallback) {
        LLResult result = fetchResult();
        Pose3D pose3D = result.getBotpose_MT2();

        if (pose3D == null || result.getBotposeTagCount() == 0) {
            return fallback;
        }

        Position position = pose3D.getPosition();
        YawPitchRollAngles angles = pose3D.getOrientation();

        double x = position.toUnit(DistanceUnit.INCH).x;
        double y = position.toUnit(DistanceUnit.INCH).y;
        double heading = angles.getYaw(AngleUnit.RADIANS);

        return new Pose2D(DistanceUnit.INCH, x, y, AngleUnit.RADIANS, heading);
    }
}