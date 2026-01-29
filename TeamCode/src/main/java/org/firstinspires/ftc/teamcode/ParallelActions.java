package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelActions extends Actions{
    private static final List<actions> objects = new ArrayList<>();

    public void addToStack(actions...obj){
        objects.addAll(Arrays.asList(obj));
    }
    public static void timeUpdate(){
        for(int i =0; i < objects.size(); i++){
            actions obj = objects.get(i);
           if(obj.run()){
               objects.remove(i);
               i--;
           }
        }
    }
}
