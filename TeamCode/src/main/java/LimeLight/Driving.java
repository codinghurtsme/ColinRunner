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
    private final Drive drive;

    public Driving(int id, HardwareMap hardwareMap){
        precision = new Precision();
        drive = new Drive(hardwareMap);
    }
    /**
     * Aligns robot using four wheels to a specified April-Tag
     */
    public void align(int id){
        if(precision.aimed(id))return;
        if(precision.degreesToTag(id)==1000)return;
        if(precision.degreesToTag(id)>0){
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
