package com.vadeen.neat.gui.panels;

import javax.swing.*;

public class MainPanel {

    private JPanel contentPane;

    private JPanel controlPanelWrapper;
    private JPanel statsPanelWrapper;
    private JPanel visualizationPanelWrapper;

    public static JPanel create(JPanel controlPanel, JPanel statsPanel, JPanel visualizationPanel) {
        return new MainPanel(controlPanel, statsPanel, visualizationPanel).contentPane;
    }

    private MainPanel(JPanel controlPanel, JPanel statsPanel, JPanel visualizationPanel) {
        this.controlPanelWrapper.add(controlPanel);
        this.statsPanelWrapper.add(statsPanel);
        this.visualizationPanelWrapper.add(visualizationPanel);
    }

    private void createUIComponents() {
        controlPanelWrapper = new JPanel();
        statsPanelWrapper = new JPanel();
    }
}
