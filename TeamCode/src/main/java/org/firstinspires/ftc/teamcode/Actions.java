package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.List;

public class Actions {

    static ElapsedTime timer = new ElapsedTime();
    abstract public static class actions {

        protected boolean isRun;

        abstract boolean run();
    }

    private static final List<actions> objects = new ArrayList<>();

    public void addToStack(actions obj) {
        objects.add(obj);
    }

    private static int index = 0;

    public static void timeUpdate() {
        int size = objects.size();

        if (index >= size) return;

        actions obj = objects.get(index);

        if (obj.run()) {
            index++;
        }
    }


}
