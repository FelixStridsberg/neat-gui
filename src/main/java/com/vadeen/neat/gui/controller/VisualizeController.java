package com.vadeen.neat.gui.controller;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.SimulationContext;
import com.vadeen.neat.gui.visualization.VisualPanel;
import com.vadeen.neat.gui.visualization.VisualizationRunner;
import com.vadeen.neat.gui.visualization.Visualizer;

public class VisualizeController {

    private final Visualizer visualizer;
    private final SimulationContext context;
    private final VisualizationRunner visualizationRunner;

    public VisualizeController(Visualizer visualizer, VisualPanel visualPanel, SimulationContext context) {
        this.visualizer = visualizer;
        this.context = context;
        this.visualizationRunner = new VisualizationRunner(visualizer, visualPanel);
    }

    public void start() {
        if (visualizer == null) {
            System.err.println("No visualizer defined.");
            return;
        }

        this.visualizationRunner.run(context.getNeat().getGeneration());
    }

    public void stop() {
        visualizationRunner.stop();
    }
}
