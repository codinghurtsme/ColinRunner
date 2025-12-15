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

    public static double kStatic(Drive drive){
        MecunumMath mm = new MecunumMath(5,5,5);
        Pose2d initialPose = drive.localizer.getPose();
        double xPose = initialPose.position.x;
        double kStatic = 0;
        double velocity = 0;
        ElapsedTime time = new ElapsedTime();
        while(velocity<.05){

        }
        return kStatic;
    }
}
