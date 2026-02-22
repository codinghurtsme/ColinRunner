//package org.firstinspires.ftc.teamcode.AllDrives;
//
//import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
//
//public class Pose2d {
//
//    public final GoBildaPinpointDriver pinpoint;
//    public double x;
//    public double y;
//    public double heading;
//    public double xVel;
//    public double yVel;
//    public double headingVel;
//    public Pose2d(double x, double y, double heading, HardwareMap hardwareMap){
//        this.x = x;
//        this.y = y;
//        this.heading = heading;
//        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
//        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
//        pinpoint.setPosX(x,DistanceUnit.INCH);
//        pinpoint.setPosY(y,DistanceUnit.INCH);
//        pinpoint.setHeading(heading,AngleUnit.DEGREES);
//    }
//
//    public Pose2d(HardwareMap hardwareMap){
//        this.x = 0;
//        this.y = 0;
//        this.heading = 0;
//        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
//        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
//        pinpoint.setPosX(x,DistanceUnit.INCH);
//        pinpoint.setPosY(y,DistanceUnit.INCH);
//        pinpoint.setHeading(heading,AngleUnit.DEGREES);
//
//    }
//
//    public void getPose(){
//        pinpoint.update();
//        this.x = pinpoint.getPosX(DistanceUnit.INCH);
//        this.y = pinpoint.getPosY(DistanceUnit.INCH);
//        this.heading = pinpoint.getHeading(AngleUnit.DEGREES);
//        this.xVel = pinpoint.getVelX(DistanceUnit.INCH);
//        this.yVel = pinpoint.getVelY(DistanceUnit.INCH);
//        this.headingVel = pinpoint.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
//
//    }
//    public void setPose(double x, double y, double heading){
//        this.x = x;
//        this.y = y;
//        this.heading = heading;
//        pinpoint.setPosX(x,DistanceUnit.INCH);
//        pinpoint.setPosY(y,DistanceUnit.INCH);
//        pinpoint.setHeading(heading,AngleUnit.DEGREES);    }
//}
