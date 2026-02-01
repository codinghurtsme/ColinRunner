package org.firstinspires.ftc.teamcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrajectoryBuilder extends ActionBuilder {
    private Pose2d pose;
    private Drive drive;

    private List<Actions> actions = new ArrayList<Actions>();
    public TrajectoryBuilder(Pose2d pose, Drive drive){
        this.pose=pose;
        this.drive = drive;
    }

    public TrajectoryBuilder(Pose2d pose, Drive drive, Object... object) {
        this(pose, drive);

        for(Object obj: object) {
            if (obj instanceof ActionBuilder.Actions) actions.addAll(Arrays.asList((Actions) obj));
            else if(obj instanceof ActionBuilder.Actions[]) actions.addAll(Arrays.asList((Actions[]) obj));
            else if(obj instanceof TrajectoryBuilder) {
                actions.addAll(((TrajectoryBuilder) obj).getActions());
            }
            // I am not completely sure what I did but I think it works ... probably ... maybe ... maybe not ... probably not
            // Why did I do this
            // Colin, don't ask what I did or how it works. I don't know.
            // Moral of the story : Screw around till something works
            // it is supposed to take any time of action, either trajectory builder or actions

        }
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

    public List<Actions> getActions() {
        return this.actions;
    }
}
