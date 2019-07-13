package com.vadeen.neat.gui.charts;

import javax.swing.*;
import java.awt.*;

/**
 * Simple auto scaling line chart.
 */
public class LineChart extends JPanel {
    private static final int VERTICAL_LINES = 10;
    private static final int PADDING = 5;

    // The scaling snaps every 10 samples.
    private static final int SCALING_SNAP = 10;

    private static final int LINE_COLOR = 0xDDDDDD;
    private static final int TEXT_COLOR = 0x999999;

    private final FloatDataset dataSet;

    public LineChart(FloatDataset dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(Color.WHITE);
        g2.setStroke(new BasicStroke(1));
        g2.clearRect(0, 0, getWidth(), getHeight());

        int leftPadding = paintVerticalAxis(g2);
        paintGraph(g2, leftPadding);
    }

    private void paintGraph(Graphics2D g, int leftPadding) {
        int samples = dataSet.size();
        if (samples == 0)
            return;

        // Actual size of the graph area.
        int height = getHeight() - PADDING*2;
        int width = getWidth() - leftPadding - PADDING;

        // Simulates more samples so graph scaling "snaps" instead of scaling at every sample.
        int sampleOffset = (SCALING_SNAP - samples%SCALING_SNAP) - 1;

        // Spacing between each point on the x axis.
        int xSpacing = width/(samples + sampleOffset);

        // Scale data to fit vertically.
        float verticalScale = height/dataSet.getMaxValue();

        // Starting point
        int lastX = leftPadding;
        int lastY = height + PADDING;

        // Draw the graph.
        for (Float value : dataSet.getValues()) {
            int x = lastX + xSpacing;
            int y = height - (int)(value.intValue()*verticalScale) + PADDING;

            g.drawLine(lastX, lastY, x, y);

            lastX = x;
            lastY = y;
        }
    }

    private int paintVerticalAxis(Graphics2D g) {
        float max = dataSet.getMaxValue();
        int height = getHeight() - PADDING/2;
        int lineSpacing = height/VERTICAL_LINES;
        int stringWidth = g.getFontMetrics().stringWidth(label(max));

        for (int i = 1; i < VERTICAL_LINES; i++) {
            int y = height - i*lineSpacing - PADDING;
            g.setColor(new Color(LINE_COLOR));
            g.drawLine(stringWidth + PADDING*2, y, getWidth() - PADDING*2, y);

            g.setColor(new Color(TEXT_COLOR));
            g.drawString(label(i*(max/VERTICAL_LINES)), PADDING, y + getTextYAdjustment(g));
        }

        return stringWidth + PADDING*2;
    }

    private int getTextYAdjustment(Graphics2D g) {
        return (int)Math.floor((g.getFontMetrics().getAscent() - 1)/2.0f);
    }

    private String label(float n) {
        return String.format("%.02f", n);
    }
}
