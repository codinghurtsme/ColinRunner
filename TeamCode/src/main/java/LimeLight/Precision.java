package LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

public class Precision {
    private double tolerance;
    /**
     * Sets tolerance
     */
    public void setTolerance(double tol){
        tolerance = tol;
    }
    /**
     * Fetches Results
     */
    private LLResult fetchResult(){
        return LimeLightBasics.validResult();
    }
    /**
     * Returns Degrees to April-Tag
     */
    public double degreesToTag(int id){
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
    /**
     * Returns if the Robot is Aimed at the April-Tag
     */
    public boolean aimed(int id){
        LLResult result = fetchResult();
        return Math.abs(degreesToTag(id)) < tolerance;
    }
}
