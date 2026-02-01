package org.firstinspires.ftc.teamcode.AllDrives;

public class FeedForwardEquations {
    private final Drive drive;
    public FeedForwardEquations(Drive drive){
        this.drive = drive;
    }
    public static int[] getTimesX(Pose2d pose, double pos){
        if(pos-pose.x==Drive.PARAMS.timeToAccelerate)
        if(pos-pose.x<Drive.PARAMS.timeToAccelerate){
            //work in maybe progress
        }
       return new int[]{1, 2, 3};
    }
}
