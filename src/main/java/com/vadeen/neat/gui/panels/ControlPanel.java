package com.vadeen.neat.gui.panels;

import com.vadeen.neat.gui.controller.EvolutionController;
import com.vadeen.neat.gui.controller.VisualizeController;

import javax.swing.*;

public class ControlPanel {

    private JPanel contentPane;

    private JButton evolutionStepButton;
    private JButton evolutionStartButton;
    private JButton evolutionStopButton;
    private JButton visualStartButton;
    private JButton visualStopButton;

    public static JPanel create(EvolutionController evolutionController, VisualizeController visualizeController) {
        return new ControlPanel(evolutionController, visualizeController).contentPane;
    }

    public ControlPanel(EvolutionController evolutionController, VisualizeController visualizeController) {
        evolutionStepButton.addActionListener(e -> evolutionController.evolve());
        evolutionStartButton.addActionListener(e -> evolutionController.run());
        evolutionStopButton.addActionListener(e -> evolutionController.stop());

        visualStartButton.addActionListener(e -> visualizeController.start());
        visualStopButton.addActionListener(e -> visualizeController.stop());
    }
}
