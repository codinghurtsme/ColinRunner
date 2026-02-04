package com.example.lib.Utils;

public class Utilities {
    public int clamp(int num, int min, int max) {
        if(num > max) return max;
        else return Math.max(num, min);
    }
}
