package com.vadeen.neat.gui.panels.settings;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.components.IntTextField;
import com.vadeen.neat.gui.components.PercentSlider;
import com.vadeen.neat.species.SpeciesFactory;

import javax.swing.*;

public class SpeciesSettingsPanel {
    private JPanel contentPane;
    private IntTextField distanceThreshold;

    public SpeciesSettingsPanel(Neat neat) {
        attach(neat.getSpeciesFactory());
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    private void attach(SpeciesFactory speciesFactory) {
        distanceThreshold.setValue(speciesFactory.getDistanceThreshold());
        distanceThreshold.setListener(speciesFactory::setDistanceThreshold);
    }

    private void createUIComponents() {
        distanceThreshold = new IntTextField();
    }
}
