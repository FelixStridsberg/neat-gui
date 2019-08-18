package com.vadeen.neat.gui.panels;

import javax.swing.*;

public class MainPanel {

    private final JPanel controlPanel;
    private final JPanel statsPanel;
    private final JPanel visualizationPanel;

    private JPanel contentPane;

    public static JPanel create(JPanel controlPanel, JPanel statsPanel, JPanel visualizationPanel) {
        return new MainPanel(controlPanel, statsPanel, visualizationPanel).contentPane;
    }

    private MainPanel(JPanel controlPanel, JPanel statsPanel, JPanel visualizationPanel) {
        this.controlPanel = controlPanel;
        this.statsPanel = statsPanel;
        this.visualizationPanel = visualizationPanel;
    }

    private void createUIComponents() {
    }
}
