package PID;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Constants {
    private double breakingStart;
    private double breakingStrength;
    private double maxPower;

    public Constants(double breakingStrength, double breakingStart){
        this.breakingStrength = breakingStrength;
        this.breakingStart = breakingStart;
        maxPower = 1;
    }
    public Constants() {
        this(0, 0);
        maxPower = 1;
    }

    public void setBreakingStart(double breakingStart){
        this.breakingStart = breakingStart;
    }
    public void setBreakingStrength(double breakingStrength){
        this.breakingStrength = breakingStrength;
    }
    public void setMaxPower(double maxPower){
        this.maxPower = maxPower;
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

}
