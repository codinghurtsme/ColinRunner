package com.example.lib.TrajectoryManager;

import com.example.lib.Utils.FPos;
import com.example.lib.Utils.SimBot;
import com.example.lib.Utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class SimAction {

    public abstract static class Action {
        FPos targetPos;
        public FPos getTargetPos() {return targetPos;}

        abstract public List<FPos> getMarkers();
    }

    public static class lineToX extends Action{

        public lineToX(SimBot bot, double X) {
            targetPos = new FPos();

            this.targetPos.setX(X + 72);
            this.targetPos.setY(bot.getPos().getY());
        }

        @Override
        public List<FPos> getMarkers() {
            return null;
        }
    }

    public static Action lineToX(SimBot bot, double X) {return new lineToX(bot, X);}
}
