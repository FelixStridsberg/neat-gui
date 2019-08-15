package com.vadeen.neat.gui.panels;

import com.vadeen.neat.Neat;
import com.vadeen.neat.genome.GenomeComparator;
import com.vadeen.neat.genome.GenomeFactory;
import com.vadeen.neat.gui.components.FloatValueChooser;
import com.vadeen.neat.gui.components.IntValueChooser;
import com.vadeen.neat.gui.components.PercentSlider;

import java.awt.event.ActionListener;

public class GenomeSettingsPanel extends SettingsDialog implements ActionListener {


    private final GenomeFactory genomeFactory;
    private final GenomeComparator genomeComparator;

    public GenomeSettingsPanel(Neat neat) {
        super();
        genomeFactory = neat.getGenomeFactory();
        genomeComparator = neat.getGenomeComparator();

        initReexpressProbabilitySlider();
        initBreedMutationProbabilitySlider();
        initNormalizeThresholdField();
        initDiffExcessFactorField();
        initDiffDisjointFactorField();
        initDiffWeightFactorField();

        initButtons();
    }

    private void initReexpressProbabilitySlider() {
        PercentSlider reexpressProb = new PercentSlider("Connection re-express probability when crossed.");
        reexpressProb.setValue(genomeFactory.getReexpressProbability());
        reexpressProb.setListener(genomeFactory::setReexpressProbability);
        add(reexpressProb);
    }

    private void initBreedMutationProbabilitySlider() {
        PercentSlider bredCrossProb = new PercentSlider("Mutation probability when crossed.");
        bredCrossProb.setValue(genomeFactory.getBreedMutationProbability());
        bredCrossProb.setListener(genomeFactory::setBreedMutationProbability);
        add(bredCrossProb);
    }

    private void initDiffExcessFactorField() {
        FloatValueChooser excessFactor = new FloatValueChooser("Diff excess gene count factor.");
        excessFactor.setValue(genomeComparator.getExcessFactor());
        excessFactor.setListener(genomeComparator::setExcessFactor);
        add(excessFactor);
    }

    private void initDiffDisjointFactorField() {
        FloatValueChooser disjointFactor = new FloatValueChooser("Diff disjoint gene count factor.");
        disjointFactor.setValue(genomeComparator.getDisjointFactor());
        disjointFactor.setListener(genomeComparator::setDisjointFactor);
        add(disjointFactor);
    }

    private void initDiffWeightFactorField() {
        FloatValueChooser weightDiffFactor = new FloatValueChooser("Diff weight factor.");
        weightDiffFactor.setValue(genomeComparator.getWeightDiffFactor());
        weightDiffFactor.setListener(genomeComparator::setWeightDiffFactor);
        add(weightDiffFactor);
    }

    private void initNormalizeThresholdField() {
        IntValueChooser normalizeThreshold = new IntValueChooser("Diff normalize gene count threshold.");
        normalizeThreshold.setValue(genomeComparator.getNormalizeThreshold());
        normalizeThreshold.setListener(genomeComparator::setNormalizeThreshold);
        add(normalizeThreshold);
    }
}
