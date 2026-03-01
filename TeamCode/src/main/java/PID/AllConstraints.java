package PID;

public class AllConstraints {
    public static PIDController translational = new PIDController(1,0,0);

    public static PIDController drive = new PIDController(1,0,0);

    public static PIDController heading = new PIDController(1,0,0);

    public static Constants constant = new Constants(2,5);


}
