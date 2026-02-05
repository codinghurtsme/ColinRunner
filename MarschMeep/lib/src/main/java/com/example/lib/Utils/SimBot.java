package com.example.lib.Utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class SimBot {
    private double angle;

    private double xIn;
    private double yIn;
    private double sizeIn;

    public SimBot(double sizeIn, double xIn, double yIn, FPos pos) {
        this.sizeIn = sizeIn;
        this.xIn = xIn;
        this.yIn = yIn;
        this.angle = pos.getHeading();
    }

    public void setPose(double xIn, double yIn, double angleRad) {
        this.xIn = xIn; this.yIn = yIn; this.angle = angleRad;
    }

    // Draw using Graphics2D where 1 unit == 1 inch
    public void drawInches(Graphics2D g2) {
        AffineTransform old = g2.getTransform();

        // translate to robot center in inches
        g2.translate(xIn, yIn);
        g2.rotate(angle);

        double half = sizeIn / 2.0;

        // body
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.setColor(Color.RED);
        g2.fill(new Rectangle.Double(-half, -half, sizeIn, sizeIn));

        // heading marker forward
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke((float)(0.5))); // stroke in inches (scaled by transform)
        g2.draw(new java.awt.geom.Line2D.Double(0, 0, half, 0));

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setTransform(old);
    }


    public void setAngle(double angleRadians) {
        this.angle = angleRadians;
    }

}
