package com.vadeen.neat.gui.panels.settings;

import com.vadeen.neat.Neat;
import com.vadeen.neat.genome.GenomeMutator;
import com.vadeen.neat.gui.components.FloatTextField;
import com.vadeen.neat.gui.components.PercentSlider;

import javax.swing.*;

public class MutatorSettingsPanel {

    private JPanel contentPane;
    private PercentSlider nodeMutationSlider;
    private PercentSlider connectionMutationSlider;
    private PercentSlider weightMutationSlider;
    private PercentSlider renewWeightMutationSlider;
    private FloatTextField weightPerturbingField;

    public MutatorSettingsPanel(Neat neat) {
        attach(neat.getMutator());
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    private void attach(GenomeMutator mutator) {
        connectionMutationSlider.setValue(mutator.getConnectionMutationProbability());
        connectionMutationSlider.setListener(mutator::setConnectionMutationProbability);

        nodeMutationSlider.setValue(mutator.getNodeMutationProbability());
        nodeMutationSlider.setListener(mutator::setNodeMutationProbability);

        weightMutationSlider.setValue(mutator.getWeightMutationProbability());
        weightMutationSlider.setListener(mutator::setWeightMutationProbability);

        renewWeightMutationSlider.setValue(mutator.getWeightMutationRenewProbability());
        renewWeightMutationSlider.setListener(mutator::setWeightMutationRenewProbability);

        weightPerturbingField.setValue(mutator.getWeightPerturbingFactor());
        weightPerturbingField.setListener(mutator::setWeightPerturbingFactor);
    }

    private void createUIComponents() {
        connectionMutationSlider = new PercentSlider();
        nodeMutationSlider = new PercentSlider();
        weightMutationSlider = new PercentSlider();
        renewWeightMutationSlider = new PercentSlider();
        weightPerturbingField = new FloatTextField();
    }
}
