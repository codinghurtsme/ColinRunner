package org.firstinspires.ftc.teamcode.AllActions;



import org.firstinspires.ftc.teamcode.AllDrives.Drive;
import org.firstinspires.ftc.teamcode.AllDrives.FeedForwardEquations;
import org.firstinspires.ftc.teamcode.AllDrives.Pose2d;
import org.firstinspires.ftc.teamcode.Exceptions.TangentialPath;
import org.firstinspires.ftc.teamcode.Exceptions.ZeroDistancePath;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrajectoryBuilder extends ActionBuilder {
    private Pose2d pose;
    private final Drive drive;

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
        private double pastVelocity;
        private boolean initialized;
        private final ElapsedTime timer = new ElapsedTime();
        private final double direction;
        private final FeedForwardEquations feed = new FeedForwardEquations(drive);
        public lineToX(double pos){
            this.pos = pos;
            if(pos == pose.x) throw new ZeroDistancePath();
            if((pose.heading<=91&&pose.heading>=89)||(pose.heading<=271&&pose.heading>=269)) throw new TangentialPath("x","lineToY");
            direction = Math.signum(pos-pose.x);
        }
        double[] times = FeedForwardEquations.getTimesX(pose,pos);
        public boolean run(){
            if(!initialized)initialized=true;timer.reset();
            if(timer.seconds()>=times[2]){
                drive.setPower(0);
                return true;
            }
            else if(timer.seconds()<times[0]){
                double acceleration = Drive.PARAMS.maxA;
                double velocity = Drive.PARAMS.kStatic + pastVelocity * Drive.PARAMS.kV + acceleration;
                pastVelocity = velocity;
                double power = velocity/ Drive.PARAMS.maxV;
                if(power>1)power=1;
                drive.setPower(power*direction);
                return false;
            }
            else if(timer.seconds()>=times[0]&&timer.seconds()<times[1]){
                drive.setPower(1*direction);
                return false;
            }
            else if(timer.seconds()>=times[1]&&timer.seconds()<times[2]){
                double acceleration = Drive.PARAMS.maxA;
                pastVelocity = Drive.PARAMS.maxV;
                double velocity = Drive.PARAMS.kStatic + pastVelocity * Drive.PARAMS.kV - acceleration;
                pastVelocity = velocity;
                double power = velocity/ Drive.PARAMS.maxV;
                if(power>1)power=1;
                if(power<0)power=0;
                drive.setPower(power*direction);
                return false;
            }
            else{
                drive.setPower(1*direction);
                return false;
            }
        }

    }

    public class lineToY extends ActionBuilder.Actions {
        private double pos;
        private double pastVelocity;
        private boolean initialized;
        private final double direction;
        private final ElapsedTime timer = new ElapsedTime();
        public lineToY(double pos){
            this.pos = pos;
            if(pos == pose.y) throw new ZeroDistancePath();
            if((pose.heading<=1&&pose.heading>=-1)||(pose.heading<=181&&pose.heading>=179)) throw new TangentialPath("x","lineToY");
            direction = Math.signum(pos-pose.y);
        }

        double[] times = FeedForwardEquations.getTimesY(pose,pos);

        public boolean run(){
            if(!initialized)initialized=true;timer.reset();
            if(timer.seconds()>=times[2]){
                drive.setPower(0);
                return true;
            }
            else if(timer.seconds()<times[0]){
                double acceleration = Drive.PARAMS.maxA;
                double velocity = Drive.PARAMS.kStatic + pastVelocity * Drive.PARAMS.kV + acceleration;
                pastVelocity = velocity;
                double power = velocity/ Drive.PARAMS.maxV;
                if(power>1)power=1;
                drive.setPower(power*direction);
                return false;
            }
            else if(timer.seconds()>=times[0]&&timer.seconds()<times[1]){
                drive.setPower(1*direction);
                return false;
            }
            else if(timer.seconds()>=times[1]&&timer.seconds()<times[2]){
                double acceleration = Drive.PARAMS.maxA;
                pastVelocity = Drive.PARAMS.maxV;
                double velocity = Drive.PARAMS.kStatic + pastVelocity * Drive.PARAMS.kV - acceleration;
                pastVelocity = velocity;
                double power = velocity/ Drive.PARAMS.maxV;
                if(power>1)power=1;
                if(power<0)power=0;
                drive.setPower(power*direction);
                return false;
            }
            else{
                drive.setPower(1*direction);
                return false;
            }
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
