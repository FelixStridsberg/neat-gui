package com.vadeen.neat.gui.charts;

import javax.swing.*;

public class LineChartTest {

    public static void main(String[] args) {

        FloatDataset dataset = new FloatDataset();
        dataset.add(0f);
        dataset.add(10f);
        dataset.add(90f);
        dataset.add(100f);
        dataset.add(100f);
        dataset.add(100f);
        dataset.add(120f);

        LineChart lineChart = new LineChart(dataset);

        JFrame frame = new JFrame("gui-test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 800, 600);
        frame.add(lineChart);
        frame.setVisible(true);
    }
}