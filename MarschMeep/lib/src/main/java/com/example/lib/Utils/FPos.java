package com.example.lib.Utils;

public class FPos {
    public double x;
    public double y;

    public double heading;

    public FPos(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public FPos() {
        this(0,0,0);
    }

    public FPos(FPos pos) {
        this(pos.getX(), pos.getY(), pos.getHeading());
    }

    public double getX() {return this.x;}
    public double getY() {return this.y;}
    public double getHeading() {return this.heading;}
    public FPos getFPos() {return new FPos(this.x, this.y, this.heading);}

    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public void setFPos(FPos newFPos) {
        this.x = newFPos.x;
        this.y = newFPos.y;
    }

    public String toString() {
        String info = "";

        info += x;
        info += ", ";
        info += y;

        return info;
    }
    public boolean equals(FPos o) {
        boolean isEqual = o.getX() == this.getX() && o.getY() == this.getY() && o.getHeading() == this.getHeading();
        return isEqual;
    }
}
