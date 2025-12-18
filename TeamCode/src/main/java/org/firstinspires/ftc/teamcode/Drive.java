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





public class Drive {
    public Localizer localizer;
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;

    public static class PARAMS {
    public static double kStatic = 0;
    public static double kV = 0;
    public static double kA = 0;
    public static double maxA = 0;
    public static double maxV = 0;
    public static double ticksPerInch = 0;


    }
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
        localizer = new PinpointLocalizer(hardwareMap, PARAMS.ticksPerInch, pose);

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
    public static double getVelocity(Drive drive) throws InterruptedException {
        drive.localizer.update();
        Pose2d pose1 = drive.localizer.getPose();

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        Thread.sleep(20); // sample window

        drive.localizer.update();
        Pose2d pose2 = drive.localizer.getPose();

        double dx = pose2.position.x - pose1.position.x;
        double dy = pose2.position.y - pose1.position.y;
        double distance = Math.sqrt((Math.pow(dx,2)+ Math.pow(dy,2)));

        double dt = timer.seconds();
        if (dt <= 0) return 0;

        return distance / dt;
    }

    public static double getAcceleration(Drive drive) throws InterruptedException {
        double v1 = getVelocity(drive);

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        Thread.sleep(40); // time between velocity samples

        double v2 = getVelocity(drive);

        double dt = timer.seconds();
        if (dt <= 0) return 0;

        return (v2 - v1) / dt;
    }

    public static void setPower(Drive drive, double p){
        drive.frontRightMotor.setPower(p);
        drive.frontLeftMotor.setPower(p);
        drive.backRightMotor.setPower(p);
        drive.backLeftMotor.setPower(p);
    }


}
