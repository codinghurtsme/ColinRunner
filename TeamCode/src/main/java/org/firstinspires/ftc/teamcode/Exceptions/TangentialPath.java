package org.firstinspires.ftc.teamcode.Exceptions;

public class TangentialPath extends RuntimeException {
    public TangentialPath(String axis, String path) {
        super("Path is Tangential to " + axis + " axis. Try " + path + " instead.");
    }
}
