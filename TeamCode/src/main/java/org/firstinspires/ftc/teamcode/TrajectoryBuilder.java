package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrajectoryBuilder {
    private Pose2d pose;
    private Drive drive;

    private List<Actions.actions> actions =  new ArrayList<Actions.actions>();

    public TrajectoryBuilder(Pose2d pose, Drive drive){
        this.pose=pose;
        this.drive = drive;
    }

    public TrajectoryBuilder(Pose2d pose, Drive drive, Actions.actions actions) {
        this(pose,drive);

        this.actions.addAll(Arrays.asList(actions));
    }

    public List<Actions.actions> getActions() {
        return this.actions;
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

    public void createTrajectory(Actions.actions ... actions){
        this.actions.addAll(Arrays.asList(actions));
    }




}
