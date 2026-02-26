package FeedForward;

import static org.firstinspires.ftc.teamcode.AllDrives.Drive.PARAMS.*;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;

public class FeedForwardEquations {
    private final Drive drive;
    private static final double maxA = Drive.PARAMS.maxA;

    public FeedForwardEquations(Drive drive){
        this.drive = drive;
    }
//    public static double[] getTimesX(Pose2d pose, double pos){
//        double distanceNeeded = Math.abs(pose.x-pos);
//        double timeToHalfway = (((.5)*(distanceNeeded))/kV)/maxA;
//        if(timeToAccelerate<timeToHalfway){
//            return new double[]{timeToHalfway,2*timeToHalfway};
//        }
//        else{
//            double distanceAfterAccelerate = distanceNeeded-distanceTraveledX(timeToAccelerate,pose);
//            double distanceToCruise = distanceAfterAccelerate-distanceTraveledX(timeToAccelerate,pose);
//            double middleTime = timeTraveledX(distanceToCruise,pose)+timeToAccelerate;
//            return new double[]{timeToAccelerate, middleTime, timeToAccelerate +middleTime};
//        }
//    }
//
//    public static double[] getTimesY(Pose2d pose, double pos){
//        double distanceNeeded = Math.abs(pose.y-pos);
//        double timeToHalfway = (((.5)*(distanceNeeded))/kV)/maxA;
//        if(timeToAccelerate<timeToHalfway){
//            return new double[]{timeToHalfway,2*timeToHalfway};
//        }
//        else{
//            double distanceAfterAccelerate = distanceNeeded-distanceTraveledY(timeToAccelerate,pose);
//            double distanceToCruise = distanceAfterAccelerate-distanceTraveledY(timeToAccelerate,pose);
//            double middleTime = timeTraveledY(distanceToCruise,pose)+timeToAccelerate;
//            double[] times = {timeToAccelerate, middleTime, timeToAccelerate+middleTime};
//            return times;
//        }
//    }
//
//    private static double distanceTraveledX(double time,Pose2d pose){
//        return (.5*(Math.pow(time,2)))*maxA*Math.cos(pose.heading);
//    }
//    private static double timeTraveledX(double distance,Pose2d pose){
//        return Math.sqrt((2*distance)/(maxA*Math.cos(pose.heading)));
//    }
//
//    private static double distanceTraveledY(double time,Pose2d pose){
//        return (.5*(Math.pow(time,2)))*maxA*Math.sin(pose.heading);
//    }
//    privte static double timeTraveledY(double distance,Pose2d pose){
//        return Math.sqrt((2*distance)/(maxA*Math.sin(pose.heading)));
//    }
    static {
        System.loadLibrary("FeedForwardEquations");
    }
    static native double[] getTimesX(double xPose, double pos,double timeToAccelerate,double kV, double maxA,double heading);
    static native double[] getTimesY(double yPose, double pos,double timeToAccelerate,double kV, double maxA,double heading);

    public static double[] getTimesX(Pose2D pose, double pos){
        return getTimesX(pose.getX(DistanceUnit.INCH), pos, timeToAccelerate,kV, maxA, pose.getHeading(AngleUnit.DEGREES));
    }
    public static double[] getTimesY(Pose2D pose, double pos){
        return getTimesY(pose.getY(DistanceUnit.INCH), pos, timeToAccelerate,kV, maxA, pose.getHeading(AngleUnit.DEGREES));
    }

}


