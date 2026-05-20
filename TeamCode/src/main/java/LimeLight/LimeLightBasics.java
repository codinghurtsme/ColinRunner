package LimeLight;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class LimeLightBasics {
   protected static Limelight3A limelight;

    public LimeLightBasics(HardwareMap hardwareMap){
        limelight = hardwareMap.get(Limelight3A.class,"limelight");
    }
    /**
     * Returns a valid LLResult
     */
    protected static LLResult validResult() {
            LLResult result = limelight.getLatestResult();
            //
            if (result != null && result.isValid()) {
                return result;
            }
        return null;
    }
    /**
     * Returns simple results tA, tX, and tY
     */
    protected static double[] simpleResults(LLResult result){
        if (result != null)return new double[]{result.getTa(),result.getTx(),result.getTy()};
        return new double[]{0,0,0};
    }
    /**
     * Returns current pipeline type and index
     */
    protected static String pipelineInfo(LLResult result){
        if (result != null) {
            int pipelineIndex = result.getPipelineIndex();
            String pipelineType = result.getPipelineType();
            return "Pipeline is index " + pipelineIndex + " and type " + pipelineType;
        }
        return "Invalid LLResult";
    }
    /**
     * Returns all April-Tags
     */
    protected static ArrayList<Integer> aprilTag(LLResult result){
        if (result != null) {
            ArrayList<Integer> tagList = new ArrayList<Integer>();
            List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();
            for (LLResultTypes.FiducialResult info : tags) {
                tagList.add(info.getFiducialId());
            }
            return tagList;
        }
        return new ArrayList<Integer>();
    }
}
