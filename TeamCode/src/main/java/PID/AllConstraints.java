package PID;

public class AllConstraints {
    public static final PIDController translational = new PIDController(1,0,0);

    public static final PIDController drive = new PIDController(1,0,0);

    public static final PIDController heading = new PIDController(1,0,0);

    public static final Constants constant = new Constants(2,5,1,5);


}
