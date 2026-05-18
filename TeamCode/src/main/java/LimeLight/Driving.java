package LimeLight;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import static org.firstinspires.ftc.teamcode.AllDrives.Drive.PARAMS.maxV;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.AllDrives.Drive;

import PID.AllConstraints;

public class Driving {
    private final Precision precision;
    private final ElapsedTime timer;
    private double lastTime;
    private int id;
    private Drive drive;

    public Driving(int id, HardwareMap hardwareMap){
        this.id=id;
        precision = new Precision(id);
        timer = new ElapsedTime();
        lastTime = 0;
        drive = new Drive(hardwareMap);
    }

    public void aline(int id){
        if(precision.aimed())return;
        if(precision.degreesToTag()==1000)return;
        if(precision.degreesToTag()>0){
            drive.setBackRight(.5);
            drive.setBackLeft(-.5);
            drive.setFrontRight(.5);
            drive.setFrontLeft(-.5);
        } else {
            drive.setBackRight(-.5);
            drive.setBackLeft(.5);
            drive.setFrontRight(-.5);
            drive.setFrontLeft(.5);
        }
    }
}
