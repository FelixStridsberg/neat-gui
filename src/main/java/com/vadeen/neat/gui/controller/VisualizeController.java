package com.vadeen.neat.gui.controller;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.visualization.VisualPanel;
import com.vadeen.neat.gui.visualization.VisualizationRunner;
import com.vadeen.neat.gui.visualization.Visualizer;

public class VisualizeController {

    private final Visualizer visualizer;
    private final VisualizationRunner visualizationRunner;

    private Neat neat;

    public VisualizeController(Visualizer visualizer, VisualPanel visualPanel, Neat neat) {
        this.visualizer = visualizer;
        this.visualizationRunner = new VisualizationRunner(visualizer, visualPanel);
        this.neat = neat;
    }

    public void start() {
        if (visualizer == null) {
            System.err.println("No visualizer defined.");
            return;
        }

        this.visualizationRunner.run(neat.getGeneration());
    }

    public void stop() {
        visualizationRunner.stop();
    }

    public void setNeat(Neat neat) {
        this.neat = neat;
    }
}
