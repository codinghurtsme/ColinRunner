package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialAction extends ActionBuilder{

    public SequentialAction(Actions...obj){
        objects.addAll(Arrays.asList(obj));    }

    private static final List<Actions> objects = new ArrayList<>();

    public void addToStack(Actions...obj){
        objects.addAll(Arrays.asList(obj));
    }

    @Override
    public boolean timeUpdate(){
        int size = objects.size();
        int index = 0;
        if (index >= size) return true;

        Actions obj = objects.get(index);

        if (obj.run()) {
            index++;
        }
        return true;
    }
}



