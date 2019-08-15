package com.vadeen.neat.gui.panels;

import com.vadeen.neat.Neat;
import com.vadeen.neat.generation.GenerationEvaluator;
import com.vadeen.neat.generation.GenerationFactory;
import com.vadeen.neat.gui.components.IntValueChooser;
import com.vadeen.neat.gui.components.PercentSlider;

import java.awt.event.ActionListener;

public class GenerationSettingsPanel extends SettingsDialog implements ActionListener {

    private final GenerationFactory generationFactory;
    private final GenerationEvaluator generationEvaluator;

    public GenerationSettingsPanel(Neat neat) {
        super();

        generationFactory = neat.getGenerationFactory();
        generationEvaluator = neat.getGenerationEvaluator();

        initPopulationField();
        initRefocusThresholdField();
        initRefocusSpeciesCountField();
        initOffspringByMutationSlider();

        initButtons();
    }

    private void initPopulationField() {
        IntValueChooser populationSize = new IntValueChooser("Population size");
        populationSize.setValue(generationFactory.getPopulationSize());
        populationSize.setListener(generationFactory::setPopulationSize);
        add(populationSize);
    }

    private void initRefocusThresholdField() {
        IntValueChooser refocusThreshold = new IntValueChooser("Refocus threshold");
        refocusThreshold.setValue(generationEvaluator.getRefocusThreshold());
        refocusThreshold.setListener(generationEvaluator::setRefocusThreshold);
        add(refocusThreshold);
    }

    private void initRefocusSpeciesCountField() {
        IntValueChooser refocusSpeciesCount = new IntValueChooser("Refocus species count");
        refocusSpeciesCount.setValue(generationFactory.getRefocusSpeciesCount());
        refocusSpeciesCount.setListener(generationFactory::setRefocusSpeciesCount);
        add(refocusSpeciesCount);
    }

    private void initOffspringByMutationSlider() {
        PercentSlider offspringByMutation = new PercentSlider("Offspring by mutation");
        offspringByMutation.setValue(generationFactory.getOffspringByMutation());
        offspringByMutation.setListener(generationFactory::setOffspringByMutation);
        add(offspringByMutation);
    }
}
