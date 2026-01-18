package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

// Non-RR imports
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
//import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Tuning extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drive drive = new Drive(hardwareMap);
        Pose2d startingPose = drive.getPose();

        int selectedTuningMode = 1;
        String mode = "kStatic";
        if(gamepad1.triangleWasPressed()) selectedTuningMode++;
        if(selectedTuningMode==6) selectedTuningMode = 1;

        if(selectedTuningMode==1) mode = "kStatic";
        if(selectedTuningMode==2) mode = "kVelocity";
        if(selectedTuningMode==3) mode = "maxVelocity";
        if(selectedTuningMode==4) mode = "maxAcceleration";
        if(selectedTuningMode==5) mode = "distanceTuning";

        telemetry.addData("Current Tuning Mode: ",mode);
        telemetry.update();


        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()){
            if(mode.equals("distanceTuning")){
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

