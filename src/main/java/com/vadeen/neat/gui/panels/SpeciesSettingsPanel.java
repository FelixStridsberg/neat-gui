package com.vadeen.neat.gui.panels;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.components.IntValueChooser;
import com.vadeen.neat.species.SpeciesFactory;

import java.awt.event.ActionListener;

public class SpeciesSettingsPanel extends SettingsDialog implements ActionListener {

    private final SpeciesFactory speciesFactory;

    public SpeciesSettingsPanel(Neat neat) {
        super();

        speciesFactory = neat.getSpeciesFactory();

        initDistanceThresholdField();

        initButtons();
    }

    private void initDistanceThresholdField() {
        IntValueChooser distance = new IntValueChooser("Genome distance threshold.");
        distance.setValue(speciesFactory.getDistanceThreshold());
        distance.setListener(speciesFactory::setDistanceThreshold);
        add(distance);
    }
}
