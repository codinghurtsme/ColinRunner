package org.firstinspires.ftc.teamcode;


public class TrajectoryBuilder extends ActionBuilder {
    private Pose2d pose;
    private Drive drive;

    public TrajectoryBuilder(Pose2d pose, Drive drive){
        this.pose=pose;
        this.drive = drive;
    }
    public void updatePose(Pose2d pose){
        this.pose = pose;
    }
    public class lineToX extends ActionBuilder.Actions {
        private double pos;
        public lineToX(double pos){
            this.pos = pos;
        }
        public boolean run(){
            return true;
        }

    }
    public void lineToY(double distance){

    }
    public void turn(double degrees){

    }
    public void turnTo(double degrees){

    }

    public boolean timeUpdate(){
        return true;
    }


}
