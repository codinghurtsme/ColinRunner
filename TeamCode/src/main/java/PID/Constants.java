package PID;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Constants {
    private double breakingStart;
    private double breakingStrength;
    private double maxPower;
    private double tolerance;

    public Constants(double breakingStrength, double breakingStart,double maxPower,double tolerance){
        this.breakingStrength = breakingStrength;
        this.breakingStart = breakingStart;
        this.maxPower = maxPower;
        this.tolerance = tolerance;
        if(maxPower>1)maxPower = 1;
    }
    public Constants() {
        this(1, 5,1,5);
        if(maxPower>1)maxPower = 1;
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

    public double getMaxPower(){
        return maxPower;
    }
    public void setMaxPower(double maxPower){
        this.maxPower = maxPower;
        if(maxPower>1)maxPower = 1;
    }
    public void setTolerance(double tolerance){
        this.tolerance = tolerance;
    }
    public double getTolerance(){
        return tolerance;
    }


}
