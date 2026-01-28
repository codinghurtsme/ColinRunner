package org.firstinspires.ftc.teamcode;

public class TrajectoryBuilder {
    private Pose2d pose;
    private Drive drive;

    public TrajectoryBuilder(Pose2d pose, Drive drive){
        this.pose=pose;
        this.drive = drive;
    }
    public void updatePose(Pose2d pose){
        this.pose = pose;
    }
    public void lineToX(double distance){

    }
    public void lineToY(double distance){

    }
    public void turn(double degrees){

    }
    public void turnTo(double degrees){

    }

    public void createTrajectory(){

    }


}
