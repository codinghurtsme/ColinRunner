package LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import java.util.*;

public class Neural {
    // Keep this local to the matching cycle to avoid compounding stale elements
    private ArrayList<LLResultTypes.DetectorResult> results = new ArrayList<>();

    /**
     * Adds Certain Objects to List
     */
    public void listObjects(String... names) {
        results.clear();
        LLResult result = LimeLightBasics.validResult();
        if (result != null ){
            List<LLResultTypes.DetectorResult> detections = result.getDetectorResults();
            if (detections != null) {
                for (LLResultTypes.DetectorResult detection : detections) {
                    for (String name : names) {
                        if (detection.getClassName().equals(name)) {
                            results.add(detection);
                        }
                    }
                }
            }
        }
    }

    /**
     * Finds Closest Element to Robot (Largest Area)
     */
    public LLResultTypes.DetectorResult closest() {
        if (results == null || results.isEmpty()) {
            return null; // Safe guard against empty list crashes
        }

        double maxArea = -1.0;
        LLResultTypes.DetectorResult bestResult = null;

        for (LLResultTypes.DetectorResult e : results) {
            if (e.getTargetArea() > maxArea) {
                maxArea = e.getTargetArea();
                bestResult = e; // Now safely locked inside the bracket logic
            }
        }
        return bestResult;
    }

    /**
     * Finds Element Next to the Biggest Group
     */
    public Object[] group() {
        if (results == null || results.isEmpty()) {
            return null; // Safe guard against empty list crashes
        }

        int maxGroupSize = 0;
        LLResultTypes.DetectorResult bestClusterItem = results.get(0);

        for (int i = 0; i < results.size(); i++) {
            double currentTx = results.get(i).getTargetXDegrees();
            int localGroupCount = 0;

            for (int j = 0; j < results.size(); j++) {
                double neighborTx = results.get(j).getTargetXDegrees();
                if (Math.abs(currentTx - neighborTx) < 8.0) {
                    localGroupCount++;
                }
            }

            if (localGroupCount > maxGroupSize) {
                maxGroupSize = localGroupCount;
                bestClusterItem = results.get(i);
            }
        }
        return new Object[]{bestClusterItem, maxGroupSize};
    }

    /**
     * Returns Best Option (Will Eventually Be An Object)
     */
    public String bestLocation(double biasToLocation) {
        Object[] groupData = group();
        LLResultTypes.DetectorResult closestItem = closest();
        if (groupData != null && groupData[0] != null && closestItem != null) {
            LLResultTypes.DetectorResult groupItem = (LLResultTypes.DetectorResult) groupData[0];

            double closeness = closestItem.getTargetArea();
            int groupSize = (int) groupData[1]; // Correct type cast to integer count
            double groupClose = groupItem.getTargetArea();

            if ((closeness * biasToLocation) > (groupSize * groupClose)) {
                return "Use Closest";
            }
            return "Use Group";
        }

        return "Suck on a Rock";
    }
}
