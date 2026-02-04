package com.example.lib.Utils;


import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.geom.Line2D;

import javax.imageio.ImageIO;

public class PathFrame {
    private FieldPanel fieldPanel;
    private JPanel controlPanel;
    private SimBot robot;
    private BufferedImage image;
    private JFrame frame;

    private JTextArea log;

    private FPos clickPoint;
    private ArrayList<Point2D.Double> path = new ArrayList<>();



    public PathFrame() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());


        fieldPanel = new FieldPanel();
        frame.add(fieldPanel, BorderLayout.CENTER);
        controlPanel = createControlPanel();
        controlPanel.setPreferredSize(new Dimension(300, 0));
        frame.add(controlPanel, BorderLayout.EAST);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public PathFrame(String imageFilePath) {

        frame = new JFrame();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        frame.setVisible(true);
    }

    private JPanel createControlPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(new Color(0x12, 0x14, 0x16)); // dark panel
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Top controls
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        top.setOpaque(false);

        JButton addObstacle = styledButton("Add Obstacle");
        JButton animate = styledButton("Animate");
        JButton clear = styledButton("Clear");

        top.add(addObstacle);
        top.add(animate);
        top.add(clear);
        p.add(top, BorderLayout.NORTH);

        // Middle: status/log area
        log = new JTextArea();
        log.setEditable(false);
        log.setBackground(new Color(0x10, 0x12, 0x14));
        log.setForeground(new Color(0xDD, 0xDD, 0xDD));
        log.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,80)),
                BorderFactory.createEmptyBorder(8,8,8,8)
        ));
        p.add(new JScrollPane(log), BorderLayout.CENTER);


        // Wire actions

        addObstacle.addActionListener(e -> { fieldPanel.addRandomObstacleInches(); log.append("Added obstacle\n"); });
        animate.addActionListener(e -> { fieldPanel.animateAlongPathInches(); log.append("Animating\n"); });
        clear.addActionListener(e -> { fieldPanel.clearObstacles(); log.append("Cleared\n"); });


        return p;
    }

    private JButton styledButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(new Color(0x2A, 0x2C, 0x2E));
        b.setForeground(new Color(0xEE, 0xEE, 0xEE));
        b.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        b.setOpaque(true);
        b.setFont(b.getFont().deriveFont(Font.PLAIN, 14f));
        return b;
    }



    public void addBot(int size, int width, int height, FPos pos) {
        robot = new SimBot(size, width, height, pos);
    }

    public void drawField(Graphics2D g2, int fx, int fy, int fsize) {
        int x = (int) Math.round(FieldTransform.getFieldLeft());
        int y = (int) Math.round(FieldTransform.getFieldTop());
        int size = (int) Math.round(FieldTransform.getFieldSizePx());


//        int size = Math.min(width, height);
//        int x = (width - size) / 2;
//        int y = (height - size) / 2;

        Shape roundField = new java.awt.geom.RoundRectangle2D.Double(fx, fy, fsize, fsize, 24, 24);
        g2.setClip(roundField);

        // Draw field image scaled to the rounded rect
        if (image != null) {
            g2.drawImage(image, fx, fy, fsize, fsize, null);
        } else {
            g2.setColor(new Color(0x1E, 0x20, 0x22));
            g2.fillRect(fx, fy, fsize, fsize);
        }

        g2.setClip(null);
    }

    private class FieldPanel extends JPanel {

        // animation state
        private Timer animTimer;
        private int animIndex = 0;
        private double animT = 0.0;

        private final ArrayList<RectangleObstacle> obstacles = new ArrayList<>();
        private final ArrayList<Point2D.Double> path = new ArrayList<>();


        public FieldPanel()
        {
            path.add(new Point2D.Double(20, 20));
            path.add(new Point2D.Double(40, 40));
            path.add(new Point2D.Double(80, 60));
            path.add(new Point2D.Double(120, 100));


            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        Point2D world = FieldTransform.screenToWorld(e.getX(), e.getY());
                        System.out.println("Clicked At: " + world.getX() + ", " + world.getY());
                        clickPoint = new FPos(world.getX(), world.getY(), 0);
                        log.append("Mouse Clicked At " + clickPoint + "\n");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            g2.setColor(new Color(0x10, 0x12, 0x14)); // very dark gray
            g2.fillRect(0, 0, getWidth(), getHeight());

            FieldTransform.update(getWidth(), getHeight());
            int fx = (int) Math.round(FieldTransform.getFieldLeft());
            int fy = (int) Math.round(FieldTransform.getFieldTop());
            int fsize = (int) Math.round(FieldTransform.getFieldSizePx());

            // Draw subtle drop shadow behind the field
            int shadowOffset = Math.max(6, (int)(FieldTransform.getScale() * 0.5));
            g2.setColor(new Color(0, 0, 0, 120));
            g2.fillRoundRect(fx + shadowOffset, fy + shadowOffset, fsize, fsize, 24, 24);


            drawField(g2, fx, fy, fsize);

            AffineTransform old = g2.getTransform();
            g2.transform(FieldTransform.worldToScreen());

            GridDrawer grid = new GridDrawer(FieldTransform.FIELD_INCHES);
            grid.draw(g2, 36.0, 3.0);



            if (robot != null) {
                robot.drawInches(g2);
            }

            g2.setTransform(old);
            g2.dispose();

            Graphics2D gBorder = (Graphics2D) g.create();
            gBorder.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gBorder.setColor(new Color(255, 255, 255, 20));
            gBorder.setStroke(new BasicStroke(2f));
            gBorder.drawRoundRect(fx, fy, fsize, fsize, 24, 24);
            gBorder.dispose();
        }

        public void addRandomObstacleInches() {
            double x = 10 + Math.random() * 120;
            double y = 10 + Math.random() * 120;
            obstacles.add(new RectangleObstacle(x, y, 12, 12));
            repaint();
        }

        public void clearObstacles() {
            obstacles.clear();
            repaint();
        }

        public void animateAlongPathInches() {
            if (path.size() < 2) return;
            if (animTimer != null && animTimer.isRunning()) return;

            animIndex = 0;
            animT = 0.0;
            int fps = 60;
            int durationPerSegmentMs = 1000;

            animTimer = new Timer(1000 / fps, e -> {
                Point2D.Double a = path.get(animIndex);
                Point2D.Double b = path.get(animIndex + 1);
                animT += 1.0 / (fps * (durationPerSegmentMs / 1000.0));
                if (animT > 1.0) {
                    animT = 0.0;
                    animIndex++;
                    if (animIndex >= path.size() - 1) {
                        ((Timer)e.getSource()).stop();
                        return;
                    }
                }
                double t = animT;
                double nx = a.x + t * (b.x - a.x);
                double ny = a.y + t * (b.y - a.y);
                double angle = Math.atan2(b.y - a.y, b.x - a.x);
                robot.setPose(nx, ny, angle);
                repaint();
            });
            animTimer.start();
        }

        private class RectangleObstacle {
            double x, y, w, h;
            RectangleObstacle(double x, double y, double w, double h) { this.x = x; this.y = y; this.w = w; this.h = h; }
            void drawInches(Graphics2D g2) {
                AffineTransform old = g2.getTransform();
                g2.setColor(new Color(0xCC, 0x44, 0x44, 200));
                g2.fill(new Rectangle2D.Double(x, y, w, h));
                g2.setColor(new Color(0x88, 0x22, 0x22, 200));
                g2.setStroke(new BasicStroke((float)0.3f));
                g2.draw(new Rectangle2D.Double(x, y, w, h));
                g2.setTransform(old);
            }
        }



    }

    public void animateAlongPathInches() {
        // implement ValueAnimator-like Swing Timer to move robot in inches
    }

    private void drawPathInches(Graphics2D g2) {
        if (path.size() < 2) return;
        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke((float)0.5f)); // inches
        for (int i = 0; i < path.size() - 1; i++) {
            Point2D.Double a = path.get(i);
            Point2D.Double b = path.get(i + 1);
            g2.draw(new Line2D.Double(a.x, a.y, b.x, b.y));
        }
    }

}
