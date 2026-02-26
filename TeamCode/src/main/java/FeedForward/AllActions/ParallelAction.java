package FeedForward.AllActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelAction extends ActionBuilder {
    private static final List<Actions> objects = new ArrayList<>();

    public ParallelAction(Object... object) {
        for(Object obj: object) {
            if (obj instanceof ActionBuilder.Actions) objects.addAll(Arrays.asList((Actions) obj));
            else if(obj instanceof ActionBuilder.Actions[]) objects.addAll(Arrays.asList((Actions[]) obj));
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

    public void addToStack(Actions...obj){
        objects.addAll(Arrays.asList(obj));
    }

    public void addToStack(Object... object) {
        for(Object obj: object) {
            if (obj instanceof ActionBuilder.Actions) objects.addAll(Arrays.asList((Actions) obj));
            else if(obj instanceof ActionBuilder.Actions[]) objects.addAll(Arrays.asList((Actions[]) obj));
            else if(obj instanceof TrajectoryBuilder) {
                objects.addAll(((TrajectoryBuilder) obj).getActions());
            }
        }
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
