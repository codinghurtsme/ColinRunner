package com.example.lib.TrajectoryManager;

import com.example.lib.Utils.FPos;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path {
    private List<SimAction.Action> actions;

    public Path() {actions = new ArrayList<SimAction.Action>();}
    public Path(SimAction.Action... commands) {

        actions = new ArrayList<SimAction.Action>();

        actions.addAll(Arrays.asList(commands));
    }

    public void addToPath(SimAction.Action... commands) {
        actions.addAll(Arrays.asList(commands));
    }

    public List<SimAction.Action> getActions() {return actions;}
}
