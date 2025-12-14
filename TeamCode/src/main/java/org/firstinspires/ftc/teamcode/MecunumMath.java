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
    private final PinpointLocalizer localizer;

    public MecunumMath(HardwareMap hardwareMap, Pose2d pose, double inPerTick) {
        localizer = new PinpointLocalizer(hardwareMap, inPerTick, pose);
    }

    public static double getVelocity(Drive drive) throws InterruptedException {
        ElapsedTime time = new ElapsedTime();
        drive.localizer.update();
        time.startTime();
        Pose2d pose = drive.localizer.getPose();
        time.wait(10);
        drive.localizer.update();
        Pose2d poseSecond = drive.localizer.getPose();
        double distanceTraveled = Math.sqrt((Math.pow((pose.position.x - poseSecond.position.x), 2)) + Math.pow((pose.position.y - poseSecond.position.y), 2));
        double velocity = distanceTraveled / .01;
        return velocity;
    }

    public static double getAcceleration(Drive drive) throws InterruptedException {
        ElapsedTime time = new ElapsedTime();
        time.startTime();
        double velocity = getVelocity(drive);
        time.wait(10);
        double velocitySecond = getVelocity(drive);
        double acceleration = (velocitySecond - velocity) / .01;
        return acceleration;
    }
}
