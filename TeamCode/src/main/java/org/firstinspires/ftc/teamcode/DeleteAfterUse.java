package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.Objects;

@Autonomous
public class DeleteAfterUse extends LinearOpMode {

    // This is an auto that can be used to test ColinRunner Colin. Please delete this test after it is used


    @Override
    public void runOpMode() throws InterruptedException {

        Drive drive = new Drive(hardwareMap);


        TrajectoryBuilder traj = new TrajectoryBuilder(drive.pose, drive);


        SequentialAction sequentialAction = new SequentialAction();
        TrajectoryBuilder tab1 = new ActionBuilder();
        sequentialAction.addToStack(lineToX(10));
        SequentialAction sequentialAction = new SequentialAction(
                traj
        );

        ActionBuilder.runBlocking(sequentialAction);

    }





}
