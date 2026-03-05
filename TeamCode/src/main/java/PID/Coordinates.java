package PID;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Coordinates{

    private double x;
    private double y;
    protected final DistanceUnit distanceUnit;
    private double heading;
    protected final AngleUnit headingUnit;


    public Coordinates(DistanceUnit distanceUnit,double x, double y, AngleUnit headingUnit, double heading) {
        this.x = x;
        this.y = y;
        this.distanceUnit = distanceUnit;
        this.heading = heading;
        this.headingUnit = headingUnit;
    }
    public Coordinates(double x, double y, double heading){
        distanceUnit = DistanceUnit.INCH;
        headingUnit = AngleUnit.RADIANS;
        this.x = x;
        this.y = y;
        this.heading = heading;

    }

    public Pose2D toPose2D(){
        double newX = y-72;
        double newY = x-72;
        double newHeading = heading + Math.PI/2;
        return new Pose2D(distanceUnit,newX,newY,headingUnit,newHeading);
    }

}
