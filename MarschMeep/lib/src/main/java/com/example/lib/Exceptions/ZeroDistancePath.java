package com.example.lib.Exceptions;

public class ZeroDistancePath extends RuntimeException {
    public ZeroDistancePath() {

        super("Path cannot have a length of 0.0");
    }
}
