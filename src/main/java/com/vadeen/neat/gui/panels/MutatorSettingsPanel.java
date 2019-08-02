package com.vadeen.neat.gui.panels;

import com.vadeen.neat.Neat;
import com.vadeen.neat.genome.GenomeMutator;
import com.vadeen.neat.gui.components.FloatValueChooser;
import com.vadeen.neat.gui.components.PercentSlider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MutatorSettingsPanel extends JPanel implements ActionListener {
    private static String CLOSE_ACTION = "close";

    private final GenomeMutator mutator;

    private CloseListener closeListener;

    public MutatorSettingsPanel(Neat neat) {
        this.mutator = neat.getMutator();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initConnectionMutationProbabilitySlider();
        initNodeMutationProbabilitySlider();
        initWeightMutationProbabilitySlider();
        initWeightMutationRenewProbabilitySlider();
        initWeightPerturbingFactor();

        initButtons();
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(CLOSE_ACTION) && closeListener != null)
            closeListener.close();
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

    private void initWeightPerturbingFactor() {
        FloatValueChooser perturbingFactor = new FloatValueChooser("Weight perturbing factor. (During weight mutation)");
        perturbingFactor.setValue(mutator.getWeightPerturbingFactor());
        perturbingFactor.setListener(mutator::setWeightPerturbingFactor);
        add(perturbingFactor);
    }

    private void initButtons() {
        JButton doneButton = new JButton("Done");
        doneButton.setActionCommand(CLOSE_ACTION);
        doneButton.addActionListener(this);
        add(doneButton);
    }
}
