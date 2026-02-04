package com.example.lib.Utils;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class FieldTransform {
    public static final double FIELD_INCHES = 144.0;

    private static double scale;
    private static double fieldLeft;
    private static double fieldTop;
    private static double fieldSizePx;

    public static void update(int panelWidth, int panelHeight) {
        fieldSizePx = Math.min(panelWidth, panelHeight);
        fieldLeft = (panelWidth - fieldSizePx) / 2.0;
        fieldTop = (panelHeight - fieldSizePx) / 2.0;
        scale = fieldSizePx / FIELD_INCHES;
    }

    public static AffineTransform worldToScreen() {
        AffineTransform t = new AffineTransform();
        t.translate(fieldLeft, fieldTop + fieldSizePx);

        t.scale(scale, -scale);

        return t;
    }

    public static Point2D screenToWorld(double sx, double sy) throws Exception {
        AffineTransform t = worldToScreen();
        AffineTransform inv = t.createInverse();
        return inv.transform(new Point2D.Double(sx, sy), null);
    }

    public static Point2D worldToScreenPoint(double wx, double wy) {
        AffineTransform t = worldToScreen();
        return t.transform(new Point2D.Double(wx, wy), null);
    }

    public static double getScale() { return scale; }
    public static double getFieldLeft() { return fieldLeft; }
    public static double getFieldTop() { return fieldTop; }
    public static double getFieldSizePx() { return fieldSizePx; }
}
