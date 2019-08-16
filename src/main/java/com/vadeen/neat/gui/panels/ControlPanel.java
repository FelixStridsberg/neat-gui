package com.vadeen.neat.gui.panels;

import com.vadeen.neat.gui.controller.EvolutionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The control panel contains buttons to control everything.
 */
public class ControlPanel extends JPanel implements ActionListener {
    private static final String ACTION_EVOLVE = "evolve";
    private static final String ACTION_RUN = "run";
    private static final String ACTION_PAUSE = "stop";
    private static final String ACTION_START_VISUALIZE = "start_visualize";
    private static final String ACTION_STOP_VISUALIZE = "stop_visualize";
    private final EvolutionController evolutionController;


    public ControlPanel(EvolutionController evolutionController) {
        this.evolutionController = evolutionController;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(createEvolvePanel());
        this.add(createVisualizePanel());
    }

    private JComponent createEvolvePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Evaluation"));

        Button evolve = new Button("Step");
        evolve.setActionCommand(ACTION_EVOLVE);
        evolve.addActionListener(this);
        panel.add(evolve);

        Button run = new Button("Run");
        run.setActionCommand(ACTION_RUN);
        run.addActionListener(this);
        panel.add(run);

        Button pause = new Button("Pause");
        pause.setActionCommand(ACTION_PAUSE);
        pause.addActionListener(this);
        panel.add(pause);

        return panel;
    }

        private JComponent createVisualizePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Visualization"));

        Button visualization = new Button("Start");
        visualization.setActionCommand(ACTION_START_VISUALIZE);
        visualization.addActionListener(this);
        panel.add(visualization);

        Button stopVisualize = new Button("Stop");
        stopVisualize.setActionCommand(ACTION_STOP_VISUALIZE);
        stopVisualize.addActionListener(this);
        panel.add(stopVisualize);

        return panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_EVOLVE:
                evolutionController.evolve();
                break;
            case ACTION_RUN:
                evolutionController.run();
                break;
            case ACTION_PAUSE:
                evolutionController.stop();
                break;
            case ACTION_START_VISUALIZE:
                // visualizeController.start();
//                controlListener.onStartVisualization(neat.getGenerationEvaluator().getGeneration());
                break;
            case ACTION_STOP_VISUALIZE:
                // visualizeController.stop();
//                controlListener.onStopVisualization();
                break;
        }
    }
}
