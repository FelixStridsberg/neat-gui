package com.vadeen.neat.gui.controller;

import com.vadeen.neat.gui.SimulationContext;
import com.vadeen.neat.gui.panel.settings.NetworkSettingsDialog;

import javax.swing.*;

public class SettingsController {

    private final JFrame mainFrame;
    private SimulationContext context;

    public SettingsController(JFrame rootFrame, SimulationContext context) {
        this.mainFrame = rootFrame;
        this.context = context;
    }

    public void openNetworkSettings() {
        NetworkSettingsDialog.open(mainFrame, context.getNeat());
    }
}
