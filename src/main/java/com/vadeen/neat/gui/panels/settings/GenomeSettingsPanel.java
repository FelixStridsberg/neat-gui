package com.vadeen.neat.gui.panels.settings;

import com.vadeen.neat.Neat;
import com.vadeen.neat.genome.GenomeComparator;
import com.vadeen.neat.genome.GenomeFactory;
import com.vadeen.neat.gui.components.FloatTextField;
import com.vadeen.neat.gui.components.IntTextField;
import com.vadeen.neat.gui.components.PercentSlider;

import javax.swing.*;

public class GenomeSettingsPanel {

    private JPanel contentPane;
    private PercentSlider connectionReexpressSlider;
    private PercentSlider mutationWhenCrossedSlider;
    private FloatTextField diffExcessFactorField;
    private FloatTextField diffDisjointFactorField;
    private FloatTextField diffWeightFactorField;
    private IntTextField diffNormalizeThresholdField;

    public static JPanel create(Neat neat) {
        return new GenomeSettingsPanel(neat).contentPane;
    }

    public GenomeSettingsPanel(Neat neat) {
        attach(neat.getGenomeFactory(), neat.getGenomeComparator());
    }

    private void attach(GenomeFactory genomeFactory, GenomeComparator genomeComparator) {
        connectionReexpressSlider.setValue(genomeFactory.getReexpressProbability());
        connectionReexpressSlider.setListener(genomeFactory::setReexpressProbability);

        mutationWhenCrossedSlider.setValue(genomeFactory.getBreedMutationProbability());
        mutationWhenCrossedSlider.setListener(genomeFactory::setBreedMutationProbability);

        diffExcessFactorField.setValue(genomeComparator.getExcessFactor());
        diffExcessFactorField.setListener(genomeComparator::setExcessFactor);

        diffDisjointFactorField.setValue(genomeComparator.getDisjointFactor());
        diffDisjointFactorField.setListener(genomeComparator::setDisjointFactor);

        diffWeightFactorField.setValue(genomeComparator.getWeightDiffFactor());
        diffWeightFactorField.setListener(genomeComparator::setWeightDiffFactor);

        diffNormalizeThresholdField.setValue(genomeComparator.getNormalizeThreshold());
        diffNormalizeThresholdField.setListener(genomeComparator::setNormalizeThreshold);
    }

    private void createUIComponents() {
        connectionReexpressSlider = new PercentSlider();
        mutationWhenCrossedSlider = new PercentSlider();
    }
}
