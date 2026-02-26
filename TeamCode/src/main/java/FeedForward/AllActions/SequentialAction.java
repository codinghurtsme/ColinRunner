package FeedForward.AllActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequentialAction extends ActionBuilder {

    public SequentialAction(Object... object) {

        for(Object obj: object) {
            if (obj instanceof ActionBuilder.Actions) objects.addAll(Arrays.asList((Actions) obj));
            else if(obj instanceof ActionBuilder.Actions[]) objects.addAll(Arrays.asList((Actions[]) obj));
            else if(obj instanceof TrajectoryBuilder) {
                objects.addAll(((TrajectoryBuilder) obj).getActions());
            }
        }


    }

    public SequentialAction(Actions...obj){
        objects.addAll(Arrays.asList(obj));    }

    private static final List<Actions> objects = new ArrayList<>();

    public void addToStack(Actions...obj){
        objects.addAll(Arrays.asList(obj));
    }

    public void addToStack(Object... object) {

        for(Object obj: object) {
            if (obj instanceof Actions) objects.addAll(Arrays.asList((Actions) obj));
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

        Actions obj = objects.get(index);

        if (obj.run()) {
            index++;
        }
        return true;
    }
}



