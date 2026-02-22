package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;

public class Tuning extends LinearOpMode {

    public enum TUNINGMODES {
        KSTATIC(1),
        KVELOCITY(2),
        MAXVELOCITY(3),
        MAXACCELERATION(4);

        private final int value;

        private TUNINGMODES(int value) {this.value = value;}

        public int getVal() {return value;}
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Drive drive = new Drive(hardwareMap);
        Pose2D startingPose = drive.getPose();

        int selectedTuningMode = 1;
        TUNINGMODES mode = TUNINGMODES.KSTATIC;
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


        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()){
            if(mode.getVal() == 1){
                telemetry.addLine("Let Robot Run Until It Stops");
                double value = drive.kStatic();
                telemetry.addData("KStatic Equals",value);

            }
            if(mode.getVal() == 2){
                telemetry.addLine("Let Robot Run Until It Runs and Stops");
                double value = drive.kVelocity();
                telemetry.addData("KVelocity Equals",value);

            }if(mode.getVal() == 3){
                telemetry.addLine("Let Robot Run Until It Runs Three Times");
                double value = drive.maxVelocity();
                telemetry.addData("Max Velocity Equals",value);

            }if(mode.getVal() == 4){
                telemetry.addLine("Let Robot Run Until It Runs Three Times");
                double value = drive.maxAcceleration();
                telemetry.addData("Max Acceleration Equals",value);

            }

            telemetry.update();
        }
    }
}

