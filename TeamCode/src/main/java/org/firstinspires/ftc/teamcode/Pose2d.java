package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Pose2d {

    public final GoBildaPinpointDriver pinpoint;
    public double x;
    public double y;
    public double heading;
    public Pose2d(double x, double y, double heading, HardwareMap hardwareMap){
        this.x = x;
        this.y = y;
        this.heading = heading;
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

    }

    public Pose2d(HardwareMap hardwareMap){
        this.x = 0;
        this.y = 0;
        this.heading = 0;
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

    }

    public void getPose(){
        pinpoint.update();
        this.x = pinpoint.getPosX(DistanceUnit.INCH);
        this.y = pinpoint.getPosY(DistanceUnit.INCH);
        this.heading = pinpoint.getHeading(AngleUnit.RADIANS);
    }
    public void setPose(double x, double y, double heading){
        this.x = x;
        this.y = y;
        this.heading = heading;
    }
}
