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
    public static double kStatic(Drive drive, MecunumMath math) throws InterruptedException {
        double power = 0;
        double velocity = 0;
        int iterations = 0;
        while(velocity<.05 && iterations<100){
            Thread.sleep(100);
            Drive.setPower(drive, power);
            velocity = math.getVelocity(drive);
            power+=.01;
            iterations++;
        }
        Drive.setPower(drive,0);
        return power;
    }
}
