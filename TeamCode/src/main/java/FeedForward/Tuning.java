package FeedForward;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;


@TeleOp
public class Tuning extends OpMode {

    public enum TUNINGMODES {
        KSTATIC(1),
        KVELOCITY(2),
        MAXVELOCITY(3),
        MAXACCELERATION(4);

        private final int value;

        private TUNINGMODES(int value) {this.value = value;}

        public int getVal() {return value;}
    }
    Drive drive = new Drive(hardwareMap);

    int selectedTuningMode = 1;
    TUNINGMODES mode = TUNINGMODES.KSTATIC;
    @Override
    public void init(){
        drive.onStart();
        Pose2D startingPose = drive.getPose();

    }



    @Override
    public void loop(){

        if(gamepad1.triangleWasPressed()) selectedTuningMode++;
        if(selectedTuningMode==5) selectedTuningMode = 1;

        switch (selectedTuningMode) {
            default: {
                selectedTuningMode = 1;
            }
            case 1: {
                mode = TUNINGMODES.KSTATIC;
            } break;
            case 2: {
                mode = TUNINGMODES.KVELOCITY;
            } break;
            case 3: {
                mode = TUNINGMODES.MAXVELOCITY;
            } break;
            case 4: {
                mode = TUNINGMODES.MAXACCELERATION;
            } break;


        }

        telemetry.addData("Current Tuning Mode: ",mode);
        telemetry.update();

        if(mode.getVal() == 1){
            telemetry.addLine("Let Robot Run Until It Stops");
            double value = 0;
            try {
                value = drive.kStatic();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            telemetry.addData("KStatic Equals",value);
        }

        if(mode.getVal() == 2){
            telemetry.addLine("Let Robot Run Until It Runs and Stops");
            double value = 0;
            try {
                value = drive.kVelocity();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            telemetry.addData("KVelocity Equals",value);
        }

        if(mode.getVal() == 3){
            telemetry.addLine("Let Robot Run Until It Runs Three Times");
            double value = 0;
            try {
                value = drive.maxVelocity();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            telemetry.addData("Max Velocity Equals",value);

        }

        if(mode.getVal() == 4){

            telemetry.addLine("Let Robot Run Until It Runs Three Times");
            double value = 0;
            try {
                value = drive.maxAcceleration();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            telemetry.addData("Max Acceleration Equals",value);
           }

        telemetry.update();
    }
}

