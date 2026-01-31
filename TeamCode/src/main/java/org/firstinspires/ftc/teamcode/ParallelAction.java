package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelAction extends ActionBuilder{
    private static final List<Actions> objects = new ArrayList<>();

    public void addToStack(Actions...obj){
        objects.addAll(Arrays.asList(obj));
    }

    @Override
    public boolean timeUpdate(){
        for(int i =0; i < objects.size(); i++){
            Actions obj = objects.get(i);
           if(obj.run()){
               objects.remove(i);
               i--;
           }
        }
        return true;
    }
}
