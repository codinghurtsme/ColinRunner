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
    Pose2d i = new Pose2d(0,0,0);

    Drive drive = new Drive(hardwareMap,i);
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
        if(gamepad1.aWasPressed()){

        }
    }
}

