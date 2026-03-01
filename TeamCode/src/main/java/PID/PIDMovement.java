package PID;
import static org.firstinspires.ftc.teamcode.AllDrives.Drive.PARAMS.maxV;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive.PARAMS.*;


public class PIDMovement {

    private ElapsedTime timer = new ElapsedTime();
    private double lastTime;

    public PIDMovement(){
        lastTime =  0;
    }


    public double[] getPowers(Pose2D current, Pose2D target, double[] powers){

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
        if (magnitude < 1e-6) magnitude = 1e-6;

        double strafePIDMulti = AllConstraints.translational.getOutput(xRobot,  dT);
        double forwardPIDMulti = AllConstraints.drive.getOutput(yRobot,  dT);

        double xScaled = (xRobot / magnitude) * strafePIDMulti;
        double yScaled = (yRobot / magnitude) * forwardPIDMulti;
        double headingScaled = AllConstraints.heading.getOutput(headingError,dT);

        double frontLeft = yScaled + xScaled + headingScaled;
        double frontRight = yScaled - xScaled - headingScaled;
        double backLeft = yScaled - xScaled + headingScaled;
        double backRight = yScaled + xScaled - headingScaled;


        double max = Math.max(Math.max(Math.abs(frontLeft),Math.abs(frontRight)), Math.max(Math.abs(backLeft),Math.abs(backRight)));

        if(max>1){
            frontLeft /= max;
            frontLeft /= max;
            backLeft /= max;
            backRight /= max;
        }



        double velRatio = max / maxV;
        velRatio = Math.min(1.0, velRatio);

        double frontLeftDelta = frontLeft - powers[0];
        double frontRightDelta = frontLeft - powers[1];
        double backLeftDelta = frontLeft - powers[2];
        double backRightDelta = frontLeft - powers[3];


        double maxDelta = Math.max(Math.max(Math.abs(frontLeftDelta),Math.abs(frontRightDelta)), Math.max(Math.abs(backLeftDelta),Math.abs(backRightDelta)));

//        tune to be higher if not quick and lower if slipping
//        higher for smaller increase per speed, lower for higher increase

        double currentMaxAccel = 8.0 - (velRatio * 6.0);

        double timeToAchieve = maxDelta / currentMaxAccel;

        double frontLeftAccel = (frontLeftDelta*maxV)/(timeToAchieve+1e-6);
        double frontRightAccel = (frontRightDelta*maxV)/(timeToAchieve+1e-6);
        double backLeftAccel = (backLeftDelta*maxV)/(timeToAchieve+1e-6);
        double backRightAccel = (backRightDelta*maxV)/(timeToAchieve+1e-6);




        frontLeft += dT * frontLeftAccel;
        frontRight += dT * frontRightAccel;
        backLeft += dT * backLeftAccel;
        backRight += dT * backRightAccel;

        return new double[]{frontLeft, frontRight, backLeft, backRight};
    }

}
