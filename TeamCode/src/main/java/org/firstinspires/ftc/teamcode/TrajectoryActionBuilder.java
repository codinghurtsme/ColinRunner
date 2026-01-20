package org.firstinspires.ftc.teamcode;

public class TrajectoryActionBuilder {

    private final Drive drive;
    public TrajectoryActionBuilder(Drive drive){
        this.drive = drive;
    }
    public void lineToX(double distance){
        double position = drive.getPositionNeeded(distance);
        boolean thereYet = drive.getIsPosition(position);
        while(!thereYet){
            double power = drive.getMotorPower(position);
            drive.setPower(power);
        }
    }
}
