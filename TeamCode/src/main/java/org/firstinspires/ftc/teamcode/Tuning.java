package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;

// Non-RR imports
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Tuning extends LinearOpMode {
    Pose2d pose = new Pose2d(0,0,0);

    Drive drive = new Drive(hardwareMap,pose);


    public double kStatic() throws InterruptedException {
        double power = 0;
        double velocity = 0;
        int iterations = 0;
        while(velocity<.05 && iterations<100){
            Thread.sleep(100);
            drive.setPower(power);
            velocity = drive.getVelocity();
            power+=.01;
            iterations++;
        }
        drive.setPower(0);
        return power;
    }

    public double kVelocity() throws InterruptedException {
        double kVSum = 0;
        int iterations = 0;
        for(double power = .02; power + Drive.PARAMS.kStatic<=1; power+=.02){
           drive.setPower(power+ Drive.PARAMS.kStatic);
           double velocity = drive.getVelocity();
           if(velocity<=0) continue;
           double kV = power/velocity;
           kVSum +=kV;
           iterations++;
        }
        return (kVSum/iterations);
    }
    public double maxVelocity() throws InterruptedException {
        double sum = 0;
        for(int i = 0; i<=3; i++){
            drive.setPower(1.0);
            Thread.sleep(500);
            double velocity = drive.getVelocity();
            sum+=velocity;
            drive.setPower(0);
            Thread.sleep(1000);
        }
        return sum/4;
    }
    public double maxAcceleration() throws InterruptedException {
        double sum = 0;
        for(int i = 0; i<=3; i++){
            drive.setPower( 1.0);
            Thread.sleep(200);
            double acceleration = drive.getAcceleration();
            sum+=acceleration;
            drive.setPower(0);
            Thread.sleep(1000);
        }
        return sum/4;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d startingPose = drive.getPose();

        int selectedTuningMode = 1;
        String mode = "kStatic";
        if(gamepad1.aWasPressed()) selectedTuningMode++;
        if(selectedTuningMode==6) selectedTuningMode = 1;

        if(selectedTuningMode==1) mode = "kStatic";
        if(selectedTuningMode==2) mode = "kVelocity";
        if(selectedTuningMode==3) mode = "maxVelocity";
        if(selectedTuningMode==4) mode = "maxAcceleration";
        if(selectedTuningMode==5) mode = "ticksPerInch";

        telemetry.addData("Current Tuning Mode: ",mode);
        telemetry.update();


        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()){
            if(mode.equals("ticksPerInch")){
                telemetry.addLine("Push Robot Striaght");
                double dx= drive.updateX()-startingPose.position.x;
                double dy= drive.updateY()-startingPose.position.y;
                telemetry.addData("Ticks Traveled",Math.hypot(dx,dy));
                telemetry.addLine("Ticks per inch = Ticks Traveled / distance traveled");
            }

            telemetry.update();
        }
    }
}

