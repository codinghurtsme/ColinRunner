package PID;

import static org.firstinspires.ftc.teamcode.AllDrives.Drive.PARAMS.maxV;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Constants {
    private double breakingStart;
    private double breakingStrength;
    private double velocity;
    private double tolerance;

    public Constants(double breakingStrength, double breakingStart,double velocity,double tolerance){
        this.breakingStrength = breakingStrength;
        this.breakingStart = breakingStart;
        this.velocity = velocity;
        this.tolerance = tolerance;
        if(velocity>maxV){
            velocity = maxV;
        }
    }
    public Constants() {
        this(1, 5,1,5);
        if(velocity>maxV){
            velocity = maxV;
        }
    }

    public void setBreakingStart(double breakingStart){
        this.breakingStart = breakingStart;
    }
    public void setBreakingStrength(double breakingStrength){
        this.breakingStrength = breakingStrength;
    }

    public double getDeceleration(Pose2D current, Pose2D target){
        double targetX = target.getX(DistanceUnit.INCH);
        double targetY = target.getY(DistanceUnit.INCH);
        double currentX = current.getX(DistanceUnit.INCH);
        double currentY = current.getY(DistanceUnit.INCH);

        double distanceLeft = Math.hypot((targetX-currentX),(targetY-currentY));
        if(distanceLeft<=breakingStart){
            return Math.pow(Math.abs(distanceLeft-breakingStart),breakingStrength);
        }
        else return 0;
    }

    public double getMaxVelocity(){
        return velocity;
    }
    public void setMaxPower(double velocity){
        this.velocity = velocity;
        if(velocity>maxV){
            velocity = maxV;
        }
    }
    public void setTolerance(double tolerance){
        this.tolerance = tolerance;
    }
    public double getTolerance(){
        return tolerance;
    }


}
