package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SequentialAction extends Actions{

    // TODO rename Actions and actions to proper types

    public SequentialAction(Object... object) {

        for(Object obj: object) {
            if (obj instanceof Actions.actions) objects.addAll(Arrays.asList((actions) obj));
            else if(obj instanceof Actions.actions[]) objects.addAll(Arrays.asList((actions[]) obj));
            else if(obj instanceof TrajectoryBuilder) {
                objects.addAll(((TrajectoryBuilder) obj).getActions());
            }
                // I am not completely sure what I did but I think it works ... probably ... maybe ... maybe not ... probably not
                // Why did I do this
                // Colin, don't ask what I did or how it works. I don't know.
                // Moral of the story : Screw around till something works
                // it is supposed to take any time of action, either trajectory builder or actions

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



