package com.example.lib.TrajectoryManager;

import com.example.lib.Utils.FPos;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<FPos> actions;

    public Path() {actions = new ArrayList<FPos>();}
    public Path(SimAction.Action... commands) {

        actions = new ArrayList<FPos>();

        for(SimAction.Action act: commands) {
            actions.addAll(act.getPoints());
        }
    }

    public void addToPath(SimAction.Action... commands) {
        for(SimAction.Action obj: commands) {
            actions.addAll(obj.getPoints());
        }
    }

    public List<FPos> getActions() {return actions;}
}
