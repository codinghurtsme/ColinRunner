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
public class Drive {
    public int tickPerInch = 5;
    public Localizer localizer;
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;


    public Drive (HardwareMap hardwareMap, Pose2d pose){
        // TODO: make sure your config has motors with these names (or change them)
        //   see https://ftc-docs.firstinspires.org/en/latest/hardware_and_software_configuration/configuring/index.html
         frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
         backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
         frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
         backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // TODO: reverse motor directions if needed
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // TODO: make sure your config has an IMU with this name (can be BNO or BHI)
        //   see https://ftc-docs.firstinspires.org/en/latest/hardware_and_software_configuration/configuring/index.htm
        localizer = new PinpointLocalizer(hardwareMap, tickPerInch, pose);
    }

    public static double updateX(Drive drive){
        drive.localizer.update();
        Pose2d pose = drive.localizer.getPose();
        double currentX = pose.position.x;
        return currentX;
    }
    public static double updateY(Drive drive){
        drive.localizer.update();
        Pose2d pose = drive.localizer.getPose();
        double currentY = pose.position.y;
        return currentY;
    }

    public static void lineToX(Drive drive,double position){
        drive.localizer.update();
        Pose2d pose = drive.localizer.getPose();
        double currentX = pose.position.x;

        boolean forwards = true;

        if((position-currentX)<0){
            while(currentX>position){
                currentX = updateX(drive);
                drive.frontRightMotor.setPower(-1);
                drive.frontLeftMotor.setPower(-1);
                drive.backRightMotor.setPower(-1);
                drive.backLeftMotor.setPower(-1);
            }
            drive.frontRightMotor.setPower(0);
            drive.frontLeftMotor.setPower(0);
            drive.backRightMotor.setPower(0);
            drive.backLeftMotor.setPower(0);

        }else{
            while(currentX<position){
                currentX = updateX(drive);
                drive.frontRightMotor.setPower(1);
                drive.frontLeftMotor.setPower(1);
                drive.backRightMotor.setPower(1);
                drive.backLeftMotor.setPower(1);
            }
            drive.frontRightMotor.setPower(0);
            drive.frontLeftMotor.setPower(0);
            drive.backRightMotor.setPower(0);
            drive.backLeftMotor.setPower(0);
        }

    }
    public static void lineToY(Drive drive,double position){
        drive.localizer.update();
        Pose2d pose = drive.localizer.getPose();
        double currentY = pose.position.y;

        boolean forwards = true;

        if((position-currentY)<0){
            while(currentY>position){
                currentY = updateY(drive);
                drive.frontRightMotor.setPower(-1);
                drive.frontLeftMotor.setPower(-1);
                drive.backRightMotor.setPower(-1);
                drive.backLeftMotor.setPower(-1);
            }
            drive.frontRightMotor.setPower(0);
            drive.frontLeftMotor.setPower(0);
            drive.backRightMotor.setPower(0);
            drive.backLeftMotor.setPower(0);

        }else{
            while(currentY<position){
                currentY = updateY(drive);
                drive.frontRightMotor.setPower(1);
                drive.frontLeftMotor.setPower(1);
                drive.backRightMotor.setPower(1);
                drive.backLeftMotor.setPower(1);
            }
            drive.frontRightMotor.setPower(0);
            drive.frontLeftMotor.setPower(0);
            drive.backRightMotor.setPower(0);
            drive.backLeftMotor.setPower(0);
        }

    }
}
