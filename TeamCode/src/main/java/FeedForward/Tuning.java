package FeedForward;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;


@TeleOp
public class Tuning extends OpMode {

    public enum TUNINGMODES {
        VELOCITY(1),
        MAXACCELERATION(4);

        private final int value;

        private TUNINGMODES(int value) {this.value = value;}

        public int getVal() {return value;}
    }
    final Drive drive = new Drive(hardwareMap);

    int selectedTuningMode = 1;
    TUNINGMODES mode = TUNINGMODES.VELOCITY;
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

            case 1: {
                mode = TUNINGMODES.VELOCITY;
            } break;
            case 2: {
                mode = TUNINGMODES.MAXACCELERATION;
            } break;
            default: {
                selectedTuningMode = 1;
            }


        }

        telemetry.addData("Current Tuning Mode: ",mode);
        telemetry.update();

        if(mode.getVal() == 1){
            telemetry.addLine("Let Robot Run Until It Stops");
            double[] values;
            try {
                values = drive.velocities();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            telemetry.addData("KStatic Equals",values[0]);
            telemetry.addData("KV Equals",values[1]);
            telemetry.addData("Max Velocity",values[2]);

        }

        if(mode.getVal() == 2){

            telemetry.addLine("Let Robot Run Until It Runs Three Times");
            double value;
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

