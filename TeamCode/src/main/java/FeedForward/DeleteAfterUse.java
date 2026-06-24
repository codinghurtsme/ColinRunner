package FeedForward;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import FeedForward.AllActions.ActionBuilder;
import FeedForward.AllActions.SequentialAction;
import FeedForward.AllActions.TrajectoryBuilder;
import PID.Path;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;

@Autonomous
public class DeleteAfterUse extends LinearOpMode {

    // This is an auto that can be used to test ColinRunner Colin. Please delete this test after it is used


    @Override
    public void runOpMode() throws InterruptedException {

        Drive drive = new Drive(hardwareMap, new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));

        Path path1 = new Path(new Pose2D(DistanceUnit.INCH, 10, 10, AngleUnit.DEGREES, 90), new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0), hardwareMap);
        waitForStart();

//        while(!path1.run()){
//            Pose2D pose = drive.getPose();
//            telemetry.addData("x", pose.getX(DistanceUnit.INCH));
//            telemetry.addData("y", pose.getY(DistanceUnit.INCH));
//            telemetry.addData("heading", pose.getHeading(AngleUnit.DEGREES));
//            telemetry.update();
//        }
//        telemetry.addData("Angry","Angry");
//        telemetry.update();
        waitForStart();

        drive.setFrontLeft(0.3);
        drive.setFrontRight(0.3);
        drive.setBackLeft(0.3);
        drive.setBackRight(0.3);

        sleep(2000);


    }


}
