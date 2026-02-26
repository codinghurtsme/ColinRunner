package PID;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;


public class PIDMovement {

    private ElapsedTime timer = new ElapsedTime();
    private double lastTime;

    public PIDMovement(){
        lastTime =  0;
    }


    public double[] getPowers(Pose2D current, Pose2D target){

        double xError = target.getX(DistanceUnit.INCH) - current.getX(DistanceUnit.INCH);
        double yError = target.getY(DistanceUnit.INCH) - current.getY(DistanceUnit.INCH);
        double currentHeading = current.getHeading(AngleUnit.RADIANS);
        double headingError = target.getHeading(AngleUnit.RADIANS) - currentHeading;
        double currentTime = timer.seconds();
        double dT = currentTime - lastTime;
        lastTime = timer.seconds();


        double xRobot = xError * Math.cos(currentHeading) + yError * Math.sin(currentHeading);
        double yRobot = -xError * Math.sin(currentHeading) + yError * Math.cos(currentHeading);

        double magnitude = Math.sqrt(Math.pow(xRobot, 2) + Math.pow(yRobot, 2));

        double strafePIDMulti = AllConstraints.translational.getOutput(xError,  dT);
        double forwardPIDMulti = AllConstraints.drive.getOutput(yError,  dT);

        double xScaled = (xRobot / magnitude) * strafePIDMulti;
        double yScaled = (yRobot / magnitude) * forwardPIDMulti;
        double headingScaled = headingError * AllConstraints.heading.getOutput(headingError,dT);

        double frontLeft = yScaled + xScaled + headingScaled;
        double frontRight = yScaled - xScaled - headingScaled;
        double backLeft = yScaled - xScaled + headingScaled;
        double backRight = yScaled + xScaled - headingScaled;
        return new double[]{frontLeft, frontRight, backLeft, backRight};
    }

}
