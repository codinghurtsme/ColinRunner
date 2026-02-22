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
#include <iostream>
#define M_PI 3.14159265358979323846


    extern "C"
    JNIEXPORT jdoubleArray JNICALL
    Java_org_firstinspires_ftc_teamcode_AllDrives_FeedForwardEquations_getTimesX(JNIEnv *env, double xPose, double pos,double timeToAccelerate,double kV, double maxA,double heading){
            double distanceNeeded = std::abs(xPose-pos);
            double timeToHalfway = (((.5)*(distanceNeeded))/kV)/maxA;
            if(timeToAccelerate<timeToHalfway){
                jdoubleArray times = env->NewDoubleArray(2);
                double* timesToReturn = new double[2]{timeToHalfway,2*timeToHalfway};
                env->SetDoubleArrayRegion(times,0,2,timesToReturn);
                return times;
            }
            else{
                double distanceAfterAccelerate = distanceNeeded-(.5*(std::pow(timeToAccelerate,2)))*maxA*((180/M_PI)*(std::cos(heading)));
                double distanceToCruise = distanceAfterAccelerate-(.5*(std::pow(timeToAccelerate,2)))*maxA*((180/M_PI)*(std::cos(heading)));
                double middleTime = std::sqrt((2*distanceToCruise)/(maxA*((180/M_PI)*(std::cos(heading)))))+timeToAccelerate;
                jdoubleArray times = env->NewDoubleArray(2);
                double* timesToReturn = new double[3]{timeToAccelerate, middleTime, timeToAccelerate +middleTime};
                env->SetDoubleArrayRegion(times,0,2,timesToReturn);
                return times;
            }
        }

    extern "C"  jdoubleArray  JNICALL
    Java_org_firstinspires_ftc_teamcode_AllDrives_FeedForwardEquations_getTimesY(JNIEnv *env, double yPose, double pos,double kV, double maxA, double timeToAccelerate, double heading){
        double distanceNeeded = std::abs(yPose-pos);
        double timeToHalfway = (((.5)*(distanceNeeded))/kV)/maxA;
        if(timeToAccelerate<timeToHalfway){
            std::vector<double> timesToReturn = {timeToHalfway,2*timeToHalfway};
        }
        else{
            double distanceAfterAccelerate = distanceNeeded-std::sqrt((2*timeToAccelerate)/((maxA*(180/M_PI)*(std::sin(heading)))));;
            double distanceToCruise = distanceAfterAccelerate-std::sqrt((2*timeToAccelerate)/((maxA*(180/M_PI)*(std::sin(heading)))));;
            double middleTime = std::sqrt((2*distanceToCruise)/((maxA*(180/M_PI)*(std::sin(heading)))))+timeToAccelerate;

            jdoubleArray times = env->NewDoubleArray(2);
            double* timesToReturn = new double[3]{timeToAccelerate, middleTime, timeToAccelerate +middleTime};
            env->SetDoubleArrayRegion(times,0,2,timesToReturn);
            return times;
        }
    }
//        double distanceTraveledX(double time,double heading,double maxA){
//            return (.5*(std::pow(time,2)))*maxA*((180/M_PI)*(std::cos(heading)));
//    }
//
//        static double timeTraveledX(double distance,double heading,double maxA){
//            return std::sqrt((2*distance)/(maxA*((180/M_PI)*(std::cos(heading)))));
//    }
//
//
//        double distanceTraveledY(double time,double heading, double maxA){
//            return (.5*(std::pow(time,2)))*maxA*((180/M_PI)*(std::sin(heading)));
//    }
//
//        double timeTraveledY(double distance,double heading, double maxA){
//            return std::sqrt((2*distance)/((maxA*(180/M_PI)*(std::sin(heading)))));
//    }
//        double toDegrees(double radians){
//        return radians*(180/M_PI);
//    }

