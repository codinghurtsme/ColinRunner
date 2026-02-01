package org.firstinspires.ftc.teamcode.AllActions;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ActionBuilder {
    abstract boolean timeUpdate();
    static ElapsedTime timer = new ElapsedTime();
    abstract protected static class Actions{

        protected boolean isRun;

        abstract boolean run();

    }


private static final List<ActionBuilder> objects = new ArrayList<>();

    public void addToStack(ActionBuilder...obj) {
        objects.addAll(Arrays.asList(obj));

    }
    private static int index = 0;

    public static void runBlocking(ActionBuilder...objs) {
        objects.addAll(Arrays.asList(objs));
        int size = objects.size();

        if (index >= size) return;

        ActionBuilder obj = objects.get(index);

        if (obj.timeUpdate()) {
            index++;
        }
    }
}


