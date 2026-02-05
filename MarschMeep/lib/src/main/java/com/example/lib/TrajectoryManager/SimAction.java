package com.example.lib.TrajectoryManager;

import com.example.lib.Utils.FPos;
import com.example.lib.Utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class SimAction {

    public abstract static class Action {
        FPos initPos;
        FPos endPos;

        FPos getInitPos() {return initPos;}
        FPos getEndPos() {return  endPos;}

        abstract List<FPos> getPoints();

    }

    public static class lineToX extends Action{

        public lineToX(FPos initPos, double X) {
            this.initPos = initPos;

            this.endPos.setX(X);
        }

        @Override
        List<FPos> getPoints() {

            double distance = Utilities.getDistance(initPos, endPos);

            ArrayList<FPos> posList = new ArrayList<>();
            FPos currentPos = new FPos(initPos);

            while(!currentPos.equals(endPos)) {
                posList.add(currentPos);
                currentPos.setX(currentPos.getX() + 0.01);
            }

            return posList;
        }
    }

    public static Action lineToX(FPos currentPos, double X) {return new lineToX(currentPos, X);}
}
