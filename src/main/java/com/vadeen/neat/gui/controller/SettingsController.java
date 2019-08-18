package com.vadeen.neat.gui.controller;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.panels.settings.NetworkSettingsDialog;

import javax.swing.*;

public class SettingsController {

    private final JFrame mainFrame;
    private Neat neat;

    public SettingsController(JFrame rootFrame, Neat neat) {
        this.mainFrame = rootFrame;
        this.neat = neat;
    }

    public void setNeat(Neat neat) {
        this.neat = neat;
    }

    public void openNetworkSettings() {
        NetworkSettingsDialog.open(mainFrame, neat);
    }
}
