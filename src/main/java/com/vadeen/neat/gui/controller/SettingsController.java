package com.vadeen.neat.gui.controller;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.panels.*;

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

    public void openMutatorSettings() {
        openDialog(new MutatorSettingsPanel(neat));
    }

    public void openGenerationSettings() {
        openDialog(new GenerationSettingsPanel(neat));
    }

    public void openGenomeSettings() {
        openDialog(new GenomeSettingsPanel(neat));
    }

    public void openSpeciesSettings() {
        openDialog(new SpeciesSettingsPanel(neat));
    }

    private void openDialog(SettingsDialog dialog) {
        JDialog d = new JDialog(mainFrame, true);
        dialog.setCloseListener(d::dispose);

        d.add(dialog);
        d.setSize(400, 500);
        d.setVisible(true);
    }
}
