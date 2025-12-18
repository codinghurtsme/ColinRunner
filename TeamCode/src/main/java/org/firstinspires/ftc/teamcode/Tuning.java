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

public class Tuning {
    public static double kStatic(Drive drive) throws InterruptedException {
        double power = 0;
        double velocity = 0;
        int iterations = 0;
        while(velocity<.05 && iterations<100){
            Thread.sleep(100);
            Drive.setPower(drive, power);
            velocity = drive.getVelocity(drive);
            power+=.01;
            iterations++;
        }
        Drive.setPower(drive,0);
        return power;
    }

    public static double kVelocity(Drive drive) throws InterruptedException {
        double kVSum = 0;
        int iterations = 0;
        for(double power = .02; power + Drive.PARAMS.kStatic<=1; power+=.02){
           Drive.setPower(drive, power+ Drive.PARAMS.kStatic);
           double velocity = Drive.getVelocity(drive);
           if(velocity<=0) continue;
           double kV = power/velocity;
           kVSum +=kV;
           iterations++;
        }
        return (kVSum/iterations);
    }
    public static double maxVelocity(Drive drive) throws InterruptedException {
        double sum = 0;
        for(int i = 0; i<=3; i++){
            Drive.setPower(drive, 1.0);
            Thread.sleep(500);
            double velocity = Drive.getVelocity(drive);
            sum+=velocity;
            Drive.setPower(drive,0);
            Thread.sleep(1000);
        }
        return sum/4;
    }
    public static double maxAcceleration(Drive drive) throws InterruptedException {
        double sum = 0;
        for(int i = 0; i<=3; i++){
            Drive.setPower(drive, 1.0);
            Thread.sleep(200);
            double acceleration = Drive.getAcceleration(drive);
            sum+=acceleration;
            Drive.setPower(drive,0);
            Thread.sleep(1000);
        }
        return sum/4;
    }

}

