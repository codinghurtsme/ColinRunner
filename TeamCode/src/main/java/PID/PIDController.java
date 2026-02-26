package PID;
public class PIDController {
    private double kProportional;
    private double kIntregal;
    private double kDerivative;
    protected double lastError;
    protected double dT;
    protected double integral;
    protected double derivative;


    public PIDController(double kP, double kI, double kD){
        kProportional = kP;
        kIntregal = kI;
        kDerivative = kD;
        dT = 0;
        integral = 0;
        lastError = 0;
    }

    public double getkP(){
        return kProportional;
    }
    public double getkI(){
        return kIntregal;
    }
    public double getkD(){
        return kDerivative;
    }

    public double getOutput(double error,double dT){
        double kP = kProportional * error;

        integral += error * dT;
        double kI = kIntregal* integral;

        derivative = (error-lastError)/dT;
        double kD = derivative * kDerivative;

        lastError = error;

        return kP + kI + kD;

    }
}




