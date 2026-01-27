package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

        static ElapsedTime time = new ElapsedTime();

        static double delta = 0;

        abstract public static class stackCommands {
            protected double delay;
            protected double startTime;
            protected boolean isRun;

            abstract void run();
            abstract void setStartTime(double start);
        }

        private static final List<stackCommands> objects = new ArrayList<>();

        public void addToStack(stackCommands obj) {
            objects.add(obj);
            obj.setStartTime(time.milliseconds());
        }

        public static void timeUpdate() {
            int size = objects.size();

            for (int i = 0; i < size;i++) {
                stackCommands obj = objects.get(i);

                delta = time.milliseconds() - obj.startTime;
                if (delta > obj.delay) {
                    obj.run();
                    objects.remove(obj);
                    size --;
                    i--;

                }
            }
        }
    }