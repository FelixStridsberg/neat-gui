package com.vadeen.neat.gui.panels;

import com.vadeen.neat.Neat;
import com.vadeen.neat.genome.GenomeMutator;
import com.vadeen.neat.gui.components.FloatTextField;
import com.vadeen.neat.gui.components.PercentSlider;

import javax.swing.*;
import java.awt.*;

public class MutatorSettingsDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private PercentSlider nodeMutationSlider;
    private PercentSlider connectionMutationSlider;
    private PercentSlider weightMutationSlider;
    private PercentSlider renewWeightMutationSlider;
    private FloatTextField weightPerturbingField;

    public static void open(JFrame owner, Neat neat) {
        MutatorSettingsDialog dialog = new MutatorSettingsDialog(owner, neat);
        dialog.pack();
        dialog.setVisible(true);
    }

    public MutatorSettingsDialog(Frame owner, Neat neat) {
        super(owner, true);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> close());

        attach(neat.getMutator());
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

    private void close() {
        dispose();
    }

    private void createUIComponents() {
        connectionMutationSlider = new PercentSlider();
        nodeMutationSlider = new PercentSlider();
        weightMutationSlider = new PercentSlider();
        renewWeightMutationSlider = new PercentSlider();
        weightPerturbingField = new FloatTextField();
    }
}
