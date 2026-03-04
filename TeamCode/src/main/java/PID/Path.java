package PID;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;

import FeedForward.AllActions.ActionBuilder;

public class Path extends ActionBuilder.Actions {

    private final Pose2D target;
    private Pose2D current;
    private final Drive drive;
    private final PIDMovement move;
    private double[] powers;
    protected double magnitude;
    private double tolerance;
    private double correctionTime;
    private final ElapsedTime timer;
    private boolean secondStage;
    private boolean finished;

    public Path(Pose2D target, Pose2D initial, HardwareMap hardwareMap){
         this.target = target;
         drive = new Drive(hardwareMap,initial);
         powers = new double[4];
         tolerance = AllConstraints.constant.getTolerance();
         move = new PIDMovement();
         timer = new ElapsedTime();
         secondStage = false;
         correctionTime = 5;
         finished = false;
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
        else if(!secondStage){
            drive.setFrontLeft(0);
            drive.setFrontRight(0);
            drive.setBackLeft(0);
            drive.setBackRight(0);
            powers[0] = 0;
            powers[1] = 0;
            powers[2] = 0;
            powers[3] = 0;
            timer.reset();
            secondStage = true;
            return false;
        }
        else if(timer.seconds()<correctionTime){

            powers = move.getPowersSecondStage(current,target,powers);
            drive.setFrontLeft(powers[0]);
            drive.setFrontRight(powers[1]);
            drive.setBackLeft(powers[2]);
            drive.setBackRight(powers[3]);
            return false;
        }
        else {
            drive.setFrontLeft(0);
            drive.setFrontRight(0);
            drive.setBackLeft(0);
            drive.setBackRight(0);
            finished = true;
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
    public void setCorrectionTime(double correctionTime){
        this.correctionTime = correctionTime;
    }
    public void setTolerance(double tolerance){
        this.tolerance = tolerance;
    }
    public boolean isBusy(){
        return finished;
    }
    public boolean atSecondStage(){
        return secondStage;
    }

}
