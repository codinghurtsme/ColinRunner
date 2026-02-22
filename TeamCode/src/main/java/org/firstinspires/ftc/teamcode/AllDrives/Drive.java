package org.firstinspires.ftc.teamcode.AllDrives;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Drive {
    public HardwareMap hardwareMap;

    public Pose2D pose;

    private final GoBildaPinpointDriver pinpoint;
    private final DcMotor frontLeftMotor;
    private final DcMotor frontRightMotor;
    private final DcMotor backLeftMotor;
    private final DcMotor backRightMotor;

    public static class PARAMS {
        public static double kStatic = 0;
        public static double kV = 0;
        public static double kA = 0;
        public static double maxA = 1;
         public static double maxV = 0;
        public static double ticksPerInch = 0;
        public static double latGains = 0;
        public static double headingGains = 0;
        public static double axialGains = 0;
        public static double timeToAccelerate = maxV/maxA;
        final double maxPower = 1/maxV;



    }
    public Drive (HardwareMap hardwareMap){
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
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        this.hardwareMap = hardwareMap;
    }

    public Drive(HardwareMap hardwareMap, Pose2D pos) {
        this(hardwareMap);
        this.pose = pos;
    }

//    public double updateX(){
//        pinpoint.update();
//        pose.getPose();
//        double currentX = pose.x;
//        return currentX;
//    }
//    public double updateY(){
//        pinpoint.update();
//        pose.getPose();
//        double currentY = pose.y;
//        return currentY;
//    }

//    public void updateAxis() {
//        updateX();
//        updateY();
//    }

//    public void setPose(double x, double y, double heading){
//        pose.setPose(x,y,heading);
//    }
    public void onStart(){
        pinpoint.recalibrateIMU();
        pinpoint.setPosition(pose);


    }
    public Pose2D getPose(){
        return pose;
    }
    public double getVelocity(){
        pinpoint.update();
        double xv = pinpoint.getVelX(DistanceUnit.INCH);
        double yv = pinpoint.getVelY(DistanceUnit.INCH);
        return Math.hypot(xv,yv);
//        getPose();
//
//        ElapsedTime timer = new ElapsedTime();
//        timer.reset();
//
//        Thread.sleep(20); // sample window
//
//        pinpoint.update();
//        Pose2D pose2 = new Pose2D();
//        pose2 = pinpoint.getPosition()
//
//        double dx = pose2.x - pose.x;
//        double dy = pose2.y - pose.y;
//        double distance = Math.sqrt((Math.pow(dx,2)+ Math.pow(dy,2)));
//
//        double dt = timer.seconds();
//        if (dt <= 0) return 0;
//
//        return distance / dt;
    }

    public double getAcceleration() throws InterruptedException {
        double v1 = getVelocity();

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        Thread.sleep(40); // time between velocity samples

        double v2 = getVelocity();

        double dt = timer.seconds();
        if (dt <= 0) return 0;

        return (v2 - v1) / dt;
    }

    public void setPower(double p){
        frontRightMotor.setPower(p);
        frontLeftMotor.setPower(p);
        backRightMotor.setPower(p);
        backLeftMotor.setPower(p);
    }



    public double kStatic() throws InterruptedException {
        double power = 0;
        double velocity = 0;
        int iterations = 0;
        while(velocity<.05 && iterations<100){
            Thread.sleep(100);
            setPower(power);
            velocity = getVelocity();
            power+=.01;
            iterations++;
        }
        setPower(0);
        return power;
    }

    public double kVelocity() throws InterruptedException {
        double kVSum = 0;
        int iterations = 0;
        for(double power = .02; power + Drive.PARAMS.kStatic<=1; power+=.02){
            setPower(power+ Drive.PARAMS.kStatic);
            double velocity = getVelocity();
            if(velocity<=0) continue;
            double kV = power/velocity;
            kVSum +=kV;
            iterations++;
        }
        setPower(0);
        return (kVSum/iterations);
    }
    public double maxVelocity() throws InterruptedException {
        double sum = 0;
        for(int i = 0; i<=3; i++){
            setPower(1.0);
            Thread.sleep(500);
            double velocity = getVelocity();
            sum+=velocity;
            setPower(0);
            Thread.sleep(1000);
        }
        setPower(0);
        return sum/4;
    }
    public double maxAcceleration() throws InterruptedException {
        double sum = 0;
        for(int i = 0; i<=3; i++){
            setPower( 1.0);
            Thread.sleep(200);
            double acceleration = getAcceleration();
            sum+=acceleration;
            setPower(0);
            Thread.sleep(1000);
        }
        setPower(0);
        return sum/4;
    }
}
