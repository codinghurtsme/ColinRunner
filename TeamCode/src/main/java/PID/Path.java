package PID;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;

import FeedForward.AllActions.ActionBuilder;

public class Path extends ActionBuilder.Actions {

    private Pose2D target;
    private Pose2D current;
    private final Drive drive;
    private final PIDMovement move;
    private double[] powers;
    protected double magnitude;
    double tolerance;

    public Path(Pose2D target, Pose2D initial, HardwareMap hardwareMap){
         drive = new Drive(hardwareMap,initial);
         powers = new double[4];
         tolerance = AllConstraints.constant.getTolerance();
         move = new PIDMovement();
    }
    public boolean run() {
        current = drive.getPose();
        if(getMagnitude()>tolerance){
           powers = move.getPowers(current,target,powers);
           drive.setFrontLeft(powers[0]);
            drive.setFrontRight(powers[1]);
            drive.setBackLeft(powers[2]);
            drive.setBackRight(powers[3]);
            return false;
        }
        else {
            return true;
        }
    }
    protected double getMagnitude(){
        current = drive.getPose();
        double targetX = target.getX(DistanceUnit.INCH);
        double targetY = target.getY(DistanceUnit.INCH);
        double currentX = current.getX(DistanceUnit.INCH);
        double currentY = current.getY(DistanceUnit.INCH);

        return Math.hypot((targetX-currentX),(targetY-currentY));
    }

}
