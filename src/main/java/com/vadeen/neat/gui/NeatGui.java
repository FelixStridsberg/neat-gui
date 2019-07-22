package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.generation.Generation;
import com.vadeen.neat.gui.visualization.VisualPanel;
import com.vadeen.neat.gui.visualization.VisualizationRunner;
import com.vadeen.neat.gui.visualization.Visualizer;
import com.vadeen.neat.io.NeatIO;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * NeatGui is a graphical user interface that takes the control of a Neat object and lets you push buttons and slide
 * sliders to manipulate to interactively evolve the network.
 */
public class NeatGui implements ControlListener {

    private final JFrame window = new JFrame("NEAT gui");
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final StatsPanel visualPanel = new StatsPanel();
    private final ControlPanel controlPanel;
    private final Visualizer visualizer;
    private final VisualizationRunner visualizationRunner;
    private final VisualPanel visualizationPanel;

    private Neat neat;

    private AutoEvolver autoEvolver;

    public NeatGui(Neat neat, Visualizer visualizer, VisualPanel vp) {
        this.neat = neat;
        this.controlPanel = new ControlPanel(neat);
        this.visualizer = visualizer;
        this.visualizationRunner = new VisualizationRunner(visualizer, vp);
        this.visualizationPanel = vp;

        controlPanel.addControlListener(this);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridBagLayout());
        wrapper.add(visualizationPanel);

        mainPanel.add(wrapper, BorderLayout.CENTER);
        mainPanel.add(visualPanel, BorderLayout.LINE_END);
        mainPanel.add(controlPanel, BorderLayout.PAGE_START);


        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1200, 680);
        window.add(mainPanel);
    }

    public void run() {
        window.setVisible(true);
    }

    @Override
    public void onEvolve() {
        Generation gen = neat.evolve();
        visualPanel.addGeneration(gen);
    }

    @Override
    public void onRun() {
        autoEvolver = new AutoEvolver(neat, visualPanel);
        autoEvolver.start();
    }

    @Override
    public void onPause() {
        autoEvolver.halt();
        try {
            autoEvolver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        autoEvolver = null;
    }

    @Override
    public void onSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(window);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                NeatIO.write(file, neat);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoad() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showOpenDialog(window);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                this.neat = NeatIO.readNeat(file, neat.getGenerationEvaluator().getEvaluator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStartVisualization(Generation generation) {
        if (visualizer == null) {
            System.err.println("No visualizer defined.");
            return;
        }

        this.visualizationRunner.run(generation);
    }

    @Override
    public void onStopVisualization() {
        if (visualizationRunner != null)
            visualizationRunner.stop();
    }
}
