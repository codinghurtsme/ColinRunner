// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("FtcRobotController");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("FtcRobotController")
//      }
//    }
#include <cmath>
#include <vector>
#include <jni.h>
class FeedForwardEquations{

    extern "C" JNIEXPORT jobjectArray JNICALL
    Java_org_firstinspires_ftc_teamcode_AllDrives_FeedForwardEquations_getTimesX(double xPose, double pos,double timeToAccelerate,double kV, double maxA,double heading){
            double distanceNeeded = std::abs(xPose-pos);
            double timeToHalfway = (((.5)*(distanceNeeded))/kV)/maxA;
            if(timeToAccelerate<timeToHalfway){
                std::vector<double> timesToReturn = {timeToHalfway,2*timeToHalfway};
                return timesToReturn;
            }
            else{
                double distanceAfterAccelerate = distanceNeeded-distanceTraveledX(timeToAccelerate,heading,maxA);
                double distanceToCruise = distanceAfterAccelerate-distanceTraveledX(timeToAccelerate,heading,maxA);
                double middleTime = timeTraveledX(distanceToCruise,heading,maxA)+timeToAccelerate;
                std::vector<double> timesToReturn = {timeToAccelerate, middleTime, timeToAccelerate +middleTime};
                return timesToReturn;
                }
        }

    extern "C" jobjectArray JNICALL
    Java_org_firstinspires_ftc_teamcode_AllDrives_FeedForwardEquations_getTimesY(double yPose, double pos,double kV, double maxA, double timeToAccelerate, double heading){
        double distanceNeeded = std::abs(yPose-pos);
        double timeToHalfway = (((.5)*(distanceNeeded))/kV)/maxA;
        if(timeToAccelerate<timeToHalfway){
            std::vector<double> timesToReturn = {timeToHalfway,2*timeToHalfway};
        }
        else{
            double distanceAfterAccelerate = distanceNeeded-distanceTraveledY(timeToAccelerate,heading,maxA);
            double distanceToCruise = distanceAfterAccelerate-distanceTraveledY(timeToAccelerate,heading,maxA);
            double middleTime = timeTraveledY(distanceToCruise,heading,maxA)+timeToAccelerate;
            std::vector<double> timesToReturn = {timeToAccelerate, middleTime, timeToAccelerate+middleTime};
            return timesToReturn;
        }
    }

    private:
        static double distanceTraveledX(double time,double heading,double maxA){
            return (.5*(std::pow(time,2)))*maxA*std::cos(heading);
    }
    private:
        static double timeTraveledX(double distance,double heading,double maxA){
            return std::sqrt((2*distance)/(maxA*std::cos(heading)));
    }

    private:
        double distanceTraveledY(double time,double heading, double maxA){
            return (.5*(std::pow(time,2)))*maxA*std::sin(heading);
    }
    private:
        double timeTraveledY(double distance,double heading, double maxA){
            return std::sqrt((2*distance)/(maxA*std::sin(heading)));
    }
};
