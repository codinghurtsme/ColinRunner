package LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

public class Precision {
    private int id;
    private double tolerance;

    public Precision(int id){
        this.id = id;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setTolerance(double tol){
        tolerance = tol;
    }
    private LLResult fetchResult(){
        return LimeLightBasics.validResult();
    }
    public double degreesToTag(){
        LLResult result = fetchResult();
        if(result!=null){
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {
                if (tag.getFiducialId() == id) {
                    return tag.getTargetXDegrees();

                }
            }
        }
        return 1000;
    }
    public boolean aimed(){
        LLResult result = fetchResult();
        if(Math.abs(degreesToTag())<tolerance)return true;
        return false;
    }
}
