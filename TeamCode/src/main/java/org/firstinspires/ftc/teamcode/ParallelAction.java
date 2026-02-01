package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ParallelAction extends Actions{

    // TODO rename Actions and actions to proper types

    public ParallelAction(Object... object) {

        for(Object obj: object) {
            if (obj instanceof actions) objects.addAll(Arrays.asList((actions) obj));
            else if(obj instanceof TrajectoryBuilder) {
                objects.addAll(((TrajectoryBuilder) obj).getActions());
            }
            // Look in Sequential Actions

        }


    }
    private static final List<actions> objects = new ArrayList<>();

    public void addToStack(actions...obj){
        objects.addAll(Arrays.asList(obj));
    }

    public void addToStack(Object... object) {

        for(Object obj: object) {
            if (obj instanceof actions) objects.addAll(Arrays.asList((actions) obj));
            else if(obj instanceof TrajectoryBuilder) {
                objects.addAll(((TrajectoryBuilder) obj).getActions());
            }

        }


    }

    @Override
    public boolean timeUpdate(){
        for(int i =0; i < objects.size(); i++){
            actions obj = objects.get(i);
           if(obj.run()){
               objects.remove(i);
               i--;
           }
        }
        return true;
    }
}
