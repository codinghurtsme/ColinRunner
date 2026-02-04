package org.firstinspires.ftc.teamcode.AllDrives;



public class FeedForwardEquations {
    private final Drive drive;
    private static final double maxA = Drive.PARAMS.maxA;
    private static final double timeToAccelerate = Drive.PARAMS.timeToAccelerate;

    public FeedForwardEquations(Drive drive){
        this.drive = drive;
    }
    public static double[] getTimesX(Pose2d pose, double pos){
        double distanceNeeded = Math.abs(pose.x-pos);
        if(pos-pose.x==Drive.PARAMS.timeToAccelerate){}
        if(pos-pose.x<Drive.PARAMS.timeToAccelerate){}
        //work in maybe progress hopefully
        else{
            double distanceAfterAccelerate = distanceNeeded-distanceTraveledX(timeToAccelerate,pose);
            double distanceToCruise = distanceAfterAccelerate-distanceTraveledX(timeToAccelerate,pose);
            double middleTime = timeTraveledX(distanceToCruise,pose)+timeToAccelerate;
            double[] times = {timeToAccelerate, middleTime, timeToAccelerate+middleTime};
            return times;
        }
        return new double[]{1, 2, 3};
    }

    public static double[] getTimesY(Pose2d pose, double pos){
        double distanceNeeded = Math.abs(pose.y-pos);
        if(pos-pose.y==Drive.PARAMS.timeToAccelerate){}
        if(pos-pose.y<Drive.PARAMS.timeToAccelerate){}
        //work in maybe progress hopefully
        else{
            double distanceAfterAccelerate = distanceNeeded-distanceTraveledY(timeToAccelerate,pose);
            double distanceToCruise = distanceAfterAccelerate-distanceTraveledY(timeToAccelerate,pose);
            double middleTime = timeTraveledY(distanceToCruise,pose)+timeToAccelerate;
            double[] times = {timeToAccelerate, middleTime, timeToAccelerate+middleTime};
            return times;
        }
        return new double[]{1, 2, 3};
    }

    private static double distanceTraveledX(double time,Pose2d pose){
        double distanceTraveled = (.5*(Math.pow(time,2)))*maxA*Math.cos(pose.heading);
        return distanceTraveled;
    }
    private static double timeTraveledX(double distance,Pose2d pose){
        double time = Math.sqrt((2*distance)/(maxA*Math.cos(pose.heading)));
        return time;
    }

    private static double distanceTraveledY(double time,Pose2d pose){
        double distanceTraveled = (.5*(Math.pow(time,2)))*maxA*Math.sin(pose.heading);
        return distanceTraveled;
    }
    private static double timeTraveledY(double distance,Pose2d pose){
        double time = Math.sqrt((2*distance)/(maxA*Math.sin(pose.heading)));
        return time;
    }
}

