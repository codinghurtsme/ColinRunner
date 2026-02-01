package org.firstinspires.ftc.teamcode.AllActions;


import org.firstinspires.ftc.teamcode.AllDrives.Drive;
import org.firstinspires.ftc.teamcode.AllDrives.Pose2d;
import org.firstinspires.ftc.teamcode.Exceptions.TangentialPath;
import org.firstinspires.ftc.teamcode.Exceptions.ZeroDistancePath;

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
            if(pos == pose.x) throw new ZeroDistancePath();
            if((pose.heading<=91&&pose.heading>=89)||(pose.heading<=271&&pose.heading>=269)) throw new TangentialPath("x","lineToY");
        }
        public boolean run(){
            return true;
        }

    }

    public class lineToY extends ActionBuilder.Actions {
        private double pos;
        public lineToY(double pos){
            this.pos = pos;
            if(pos == pose.y) throw new ZeroDistancePath();
            if((pose.heading<=1&&pose.heading>=-1)||(pose.heading<=181&&pose.heading>=179)) throw new TangentialPath("x","lineToY");
        }
        public boolean run(){
            return true;
        }

    }

    public class turn extends ActionBuilder.Actions {
        private double degrees;
        public turn(double degrees){
            this.degrees = degrees;
            if(degrees==0) throw new ZeroDistancePath();
        }
        public boolean run(){
            return true;
        }

    }

    public class turnTo extends ActionBuilder.Actions {
        private double degrees;
        public turnTo(double degrees){
            this.degrees = degrees;
            if(degrees == pose.heading) throw new ZeroDistancePath();
        }
        public boolean run(){
            return true;
        }

    }


    public boolean timeUpdate(){
        return true;
    }

    public List<Actions> getActions() {
        return this.actions;
    }
}
