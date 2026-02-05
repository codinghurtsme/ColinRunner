package com.example.lib.TrajectoryManager;

import com.example.lib.Utils.FPos;
import com.example.lib.Utils.Utilities;

import java.util.ArrayList;

public class SimAction {

    abstract protected static class Action {
        FPos initPos;
        FPos endPos;

        FPos getInitPos() {return initPos;}
        FPos getEndPos() {return  endPos;}

        abstract ArrayList<FPos> getPoints();

    }

    public class lineToX extends Action{

        public lineToX(FPos initPos, FPos endPos) {
            this.endPos = endPos;
            this.initPos = initPos;
        }

        @Override
        ArrayList<FPos> getPoints() {

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
}
