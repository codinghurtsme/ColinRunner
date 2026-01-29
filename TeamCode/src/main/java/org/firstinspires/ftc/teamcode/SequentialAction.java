package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialAction extends Actions{

    public SequentialAction(actions...obj){
        objects.addAll(Arrays.asList(obj));    }

    private static final List<actions> objects = new ArrayList<>();

    public void addToStack(actions...obj){
        objects.addAll(Arrays.asList(obj));
    }

    @Override
    public boolean timeUpdate(){
        int size = objects.size();
        int index = 0;
        if (index >= size) return true;

        actions obj = objects.get(index);

        if (obj.run()) {
            index++;
        }
        return true;
    }
}



