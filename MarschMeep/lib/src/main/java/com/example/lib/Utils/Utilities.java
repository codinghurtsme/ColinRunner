package com.example.lib.Utils;

public class Utilities {
    public int clamp(int num, int min, int max) {
        if(num > max) return max;
        else return Math.max(num, min);
    }

    public static double getDistance(FPos pos1, FPos pos2) {
        double d;
        d = Math.pow(pos2.getX() - pos1.getX(), 2) + Math.pow(pos2.getY() - pos1.getY(), 2);

        d = Math.sqrt(d);

        return d;
    }

    public static float lerp(float initVal, float endVal, float interpolationVal) {
        return (initVal + interpolationVal * (endVal - initVal));
    }

}
