package com.vadeen.neat.gui.panels.settings;

import com.vadeen.neat.Neat;
import com.vadeen.neat.generation.GenerationEvaluator;
import com.vadeen.neat.generation.GenerationFactory;
import com.vadeen.neat.gui.components.IntTextField;
import com.vadeen.neat.gui.components.PercentSlider;

import javax.swing.*;

public class GenerationSettingsPanel {

    private JPanel contentPane;
    private IntTextField populationSizeField;
    private IntTextField refocusThresholdField;
    private IntTextField refocusSpeciesCountField;
    private PercentSlider offspringByMutationSlider;

    public static JPanel create(Neat neat) {
        return new GenerationSettingsPanel(neat).contentPane;
    }

    private GenerationSettingsPanel(Neat neat) {
        attach(neat.getGenerationFactory(), neat.getGenerationEvaluator());
    }

    private void attach(GenerationFactory generationFactory, GenerationEvaluator generationEvaluator) {
        populationSizeField.setValue(generationFactory.getPopulationSize());
        populationSizeField.setListener(generationFactory::setPopulationSize);

        refocusThresholdField.setValue(generationEvaluator.getRefocusThreshold());
        refocusThresholdField.setListener(generationEvaluator::setRefocusThreshold);

        refocusSpeciesCountField.setValue(generationFactory.getRefocusSpeciesCount());
        refocusSpeciesCountField.setListener(generationFactory::setRefocusSpeciesCount);

        offspringByMutationSlider.setValue(generationFactory.getOffspringByMutation());
        offspringByMutationSlider.setListener(generationFactory::setOffspringByMutation);
    }

    private void createUIComponents() {
        offspringByMutationSlider = new PercentSlider();
    }
}
