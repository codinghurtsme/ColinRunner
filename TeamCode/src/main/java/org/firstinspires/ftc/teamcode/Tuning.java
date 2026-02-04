package org.firstinspires.ftc.teamcode;

// Non-RR imports
//import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.AllDrives.Drive;
import org.firstinspires.ftc.teamcode.AllDrives.Pose2d;

public class Tuning extends LinearOpMode {

    public enum TUNINGMODES {
        KSTATIC(1),
        KVELOCITY(2),
        MAXVELOCITY(3),
        MAXACCELERATION(4),
        DISTANCETUNING(5);

        private final int value;

        private TUNINGMODES(int value) {this.value = value;}

        public int getVal() {return value;}
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Drive drive = new Drive(hardwareMap);
        Pose2d startingPose = drive.getPose();

        int selectedTuningMode = 1;
        TUNINGMODES mode = TUNINGMODES.KSTATIC;
        if(gamepad1.triangleWasPressed()) selectedTuningMode++;
        if(selectedTuningMode==6) selectedTuningMode = 1;

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
            case 5: {
                mode = TUNINGMODES.DISTANCETUNING;
            } break;

        }

        telemetry.addData("Current Tuning Mode: ",mode);
        telemetry.update();


        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()){
            if(mode.getVal() == 5){
                telemetry.addLine("Push Robot Straight");
                double dx = drive.updateX()-startingPose.x;
                double dy = drive.updateY()-startingPose.y;
                telemetry.addData("Distance Traveled",Math.hypot(dx,dy));
                telemetry.addLine("Distanced Traveled / Actual Distance = Distance Offset");
            }

            telemetry.update();
        }
    }
}

