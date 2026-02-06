package com.example.lib;

import com.example.lib.TrajectoryManager.Path;
import com.example.lib.TrajectoryManager.SimAction;
import com.example.lib.Utils.FPos;
import com.example.lib.Utils.PathFrame;
import com.example.lib.Utils.SimBot;

import java.awt.*;

public class Runner {

    public static void main(String[] args) {

        PathFrame frame = new PathFrame();
        frame.addBot(18,18, 18, new FPos(0,0, 0));

        frame.addPath(new Path(SimAction.lineToX(10)));
    }
}