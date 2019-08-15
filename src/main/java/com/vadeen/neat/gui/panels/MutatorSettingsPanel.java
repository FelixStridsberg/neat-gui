package com.vadeen.neat.gui.panels;

import com.vadeen.neat.Neat;
import com.vadeen.neat.genome.GenomeMutator;
import com.vadeen.neat.gui.components.FloatValueChooser;
import com.vadeen.neat.gui.components.PercentSlider;

import java.awt.event.ActionListener;

public class MutatorSettingsPanel extends SettingsDialog implements ActionListener {

    private final GenomeMutator mutator;

    public MutatorSettingsPanel(Neat neat) {
        super();
        this.mutator = neat.getMutator();

        initConnectionMutationProbabilitySlider();
        initNodeMutationProbabilitySlider();
        initWeightMutationProbabilitySlider();
        initWeightMutationRenewProbabilitySlider();
        initWeightPerturbingFactorField();

        initButtons();
    }

    private void initConnectionMutationProbabilitySlider() {
        PercentSlider connectionMutationProb = new PercentSlider("Connection mutation probability");
        connectionMutationProb.setValue(mutator.getConnectionMutationProbability());
        connectionMutationProb.setListener(mutator::setConnectionMutationProbability);
        add(connectionMutationProb);
    }

    private void initNodeMutationProbabilitySlider() {
        PercentSlider nodeMutationProb = new PercentSlider("Node mutation probability");
        nodeMutationProb.setValue(mutator.getNodeMutationProbability());
        nodeMutationProb.setListener(mutator::setNodeMutationProbability);
        add(nodeMutationProb);
    }

    private void initWeightMutationProbabilitySlider() {
        PercentSlider nodeMutationProb = new PercentSlider("Weight mutation probability");
        nodeMutationProb.setValue(mutator.getWeightMutationProbability());
        nodeMutationProb.setListener(mutator::setWeightMutationProbability);
        add(nodeMutationProb);
    }

    private void initWeightMutationRenewProbabilitySlider() {
        PercentSlider nodeMutationProb = new PercentSlider("Renew weight mutation probability. (During weight mutation)");
        nodeMutationProb.setValue(mutator.getWeightMutationRenewProbability());
        nodeMutationProb.setListener(mutator::setWeightMutationRenewProbability);
        add(nodeMutationProb);
    }

    private void initWeightPerturbingFactorField() {
        FloatValueChooser perturbingFactor = new FloatValueChooser("Weight perturbing factor. (During weight mutation)");
        perturbingFactor.setValue(mutator.getWeightPerturbingFactor());
        perturbingFactor.setListener(mutator::setWeightPerturbingFactor);
        add(perturbingFactor);
    }
}
