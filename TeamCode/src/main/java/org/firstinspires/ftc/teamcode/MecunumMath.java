package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
public class MecunumMath {
    private PinpointLocalizer localizer;
    public MecunumMath(HardwareMap hardwareMap,Pose2d pose, double inPerTick){
        localizer = new PinpointLocalizer(hardwareMap,inPerTick,pose);
    }
    public static double acceleration(Drive drive) {
       return 5;
    }
}
