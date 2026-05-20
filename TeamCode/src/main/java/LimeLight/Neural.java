package LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import java.util.*;

public class Neural {
    private static  ArrayList<LLResultTypes.DetectorResult> results = new ArrayList<>();
    /**
     * Adds Certain Objects to List
     */
    public static void listObjects(String... names) {
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
    public static LLResultTypes.DetectorResult closest() {
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
     * Finds Closest Certain Element to Robot (Largest Area)
     */
    public static LLResultTypes.DetectorResult closest(String name) {
        if (results == null || results.isEmpty()) {
            return null; // Safe guard against empty list crashes
        }

        double maxArea = -1.0;
        LLResultTypes.DetectorResult bestResult = null;

        for (LLResultTypes.DetectorResult e : results) {
            if (e.getClassName().equals(name)&&e.getTargetArea() > maxArea) {
                maxArea = e.getTargetArea();
                bestResult = e; // Now safely locked inside the bracket logic
            }
        }
        return bestResult;
    }
    /**
     * Finds Element Next to the Biggest Group
     */
    public static Object[] group() {
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
     * Finds Certain Element Next to the Biggest Group
     */
    public static Object[] group(String name) {
        if (results == null || results.isEmpty()) {
            return null; // Safe guard against empty list crashes
        }

        int maxGroupSize = 0;
        LLResultTypes.DetectorResult bestClusterItem = results.get(0);

        for (int i = 0; i < results.size(); i++) {
            if(results.get(i).getClassName().equals(name)) {
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
        }
        return new Object[]{bestClusterItem, maxGroupSize};
    }
    /**
     * Returns Best Option
     */
    public static LLResultTypes.DetectorResult bestLocation(double biasToLocation) {
        Object[] groupData = group();
        LLResultTypes.DetectorResult closestItem = closest();
        if (groupData != null && groupData[0] != null && closestItem != null) {
            LLResultTypes.DetectorResult groupItem = (LLResultTypes.DetectorResult) groupData[0];

            double closeness = closestItem.getTargetArea();
            int groupSize = (int) groupData[1]; // Correct type cast to integer count
            double groupClose = groupItem.getTargetArea();

            if ((closeness * biasToLocation) > (groupSize * groupClose)) {
                return groupItem;
            }
            return closestItem;
        }
        return null;
    }
    /**
     * Returns Best Certain Option
     */
    public static LLResultTypes.DetectorResult bestLocation(double biasToLocation, String name) {
        Object[] groupData = group(name);
        LLResultTypes.DetectorResult closestItem = closest(name);
        if (groupData != null && groupData[0] != null && closestItem != null) {
            LLResultTypes.DetectorResult groupItem = (LLResultTypes.DetectorResult) groupData[0];

            double closeness = closestItem.getTargetArea();
            int groupSize = (int) groupData[1]; // Correct type cast to integer count
            double groupClose = groupItem.getTargetArea();

            if ((closeness * biasToLocation) > (groupSize * groupClose)) {
                return groupItem;
            }
            return closestItem;
        }
        return null;
    }
    /**
     * Returns if a certain object is seen
     */
    public static boolean seesObject(String name){
        for(LLResultTypes.DetectorResult result : results){
            if(result.getClassName().equals(name))return true;
        }
        return false;
    }
}
