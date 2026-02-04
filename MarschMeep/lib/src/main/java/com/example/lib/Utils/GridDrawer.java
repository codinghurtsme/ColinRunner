package com.example.lib.Utils;

import java.awt.*;
import java.awt.geom.Line2D;

public class GridDrawer {
    private Color majorColor = new Color(0x66, 0x66, 0x66);
    private Color minorColor = new Color(0x99, 0x99, 0x99);
    private double majorStrokeIn = 0.12;
    private double minorStrokeIn = 0.04;

    private double fieldSizeIn = 144.0;

    public GridDrawer() {}

    public GridDrawer(double fieldSizeIn) {
        this.fieldSizeIn = fieldSizeIn;
    }

    public void setMajorColor(Color c) { this.majorColor = c; }
    public void setMinorColor(Color c) { this.minorColor = c; }
    public void setMajorStrokeIn(double s) { this.majorStrokeIn = s; }
    public void setMinorStrokeIn(double s) { this.minorStrokeIn = s; }

    public void draw(Graphics2D g2, double majorSpacingIn, double minorSpacingIn) {

        Stroke oldStroke = g2.getStroke();
        Color oldColor = g2.getColor();


        g2.setColor(minorColor);
        g2.setStroke(new BasicStroke((float) minorStrokeIn, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        for (double x = 0; x <= fieldSizeIn; x += minorSpacingIn) {
            g2.draw(new Line2D.Double(x, 0, x, fieldSizeIn));
        }
        for (double y = 0; y <= fieldSizeIn; y += minorSpacingIn) {
            g2.draw(new Line2D.Double(0, y, fieldSizeIn, y));
        }


        g2.setColor(majorColor);
        g2.setStroke(new BasicStroke((float) majorStrokeIn, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        for (double x = 0; x <= fieldSizeIn; x += majorSpacingIn) {
            g2.draw(new Line2D.Double(x, 0, x, fieldSizeIn));
        }
        for (double y = 0; y <= fieldSizeIn; y += majorSpacingIn) {
            g2.draw(new Line2D.Double(0, y, fieldSizeIn, y));
        }


        g2.setStroke(oldStroke);
        g2.setColor(oldColor);
    }
}
