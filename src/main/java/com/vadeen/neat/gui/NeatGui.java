package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.generation.Generation;
import com.vadeen.neat.gui.controls.ControlListener;
import com.vadeen.neat.gui.controls.FileMenuListener;
import com.vadeen.neat.gui.controls.SettingsMenuListener;
import com.vadeen.neat.gui.menus.FileMenu;
import com.vadeen.neat.gui.menus.SettingsMenu;
import com.vadeen.neat.gui.panels.*;
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
public class NeatGui implements ControlListener, FileMenuListener, SettingsMenuListener {

    private final JFrame mainFrame = new JFrame("NEAT gui");

    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final StatsPanel visualPanel = new StatsPanel();
    private final VisualPanel visualizationPanel;
    private final ControlPanel controlPanel;

    private final JMenuBar menuBar = new JMenuBar();

    private final Visualizer visualizer;
    private final VisualizationRunner visualizationRunner;

    private Neat neat;

    private AutoEvolver autoEvolver;

    public NeatGui(Neat neat, Visualizer visualizer, VisualPanel vp) {
        this.neat = neat;
        this.controlPanel = new ControlPanel(neat);
        this.visualizer = visualizer;
        this.visualizationRunner = new VisualizationRunner(visualizer, vp);
        this.visualizationPanel = vp;

        initMenus();
        initPanels();
    }

    public void run() {
        mainFrame.setVisible(true);
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

    private void initMenus() {
        FileMenu fileMenu = new FileMenu();
        fileMenu.addListener(this);
        menuBar.add(fileMenu);

        SettingsMenu settingsMenu = new SettingsMenu();
        settingsMenu.addListener(this);
        menuBar.add(settingsMenu);

        mainFrame.setJMenuBar(menuBar);
    }

    private void initPanels() {
        controlPanel.addControlListener(this);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridBagLayout());
        wrapper.add(visualizationPanel);

        mainPanel.add(wrapper, BorderLayout.CENTER);
        mainPanel.add(visualPanel, BorderLayout.LINE_END);
        mainPanel.add(controlPanel, BorderLayout.PAGE_START);


        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 680);
        mainFrame.add(mainPanel);
    }

    @Override
    public void onSaveNetwork() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(mainFrame);
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
    public void onLoadNetwork() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showOpenDialog(mainFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                this.neat = NeatIO.readNeat(file, neat.getGenerationEvaluator().getEvaluator());
                controlPanel.setNeat(neat);
                visualPanel.addGeneration(neat.getGeneration());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onExit() {
        // Just halt for now... Yolo!
        System.exit(0);
    }

    @Override
    public void onMutationSettings() {
        MutatorSettingsPanel panel = new MutatorSettingsPanel(neat);

        JDialog d = new JDialog(mainFrame, true);
        d.add(panel);
        d.setSize(400, 500);

        panel.setCloseListener(d::dispose);

        d.setVisible(true);
    }

    @Override
    public void onGenerationSettings() {
        GenerationSettingsPanel panel = new GenerationSettingsPanel(neat);

        JDialog d = new JDialog(mainFrame, true);
        d.add(panel);
        d.setSize(400, 500);

        panel.setCloseListener(d::dispose);

        d.setVisible(true);
    }

    @Override
    public void onGenomeSettings() {
        GenomeSettingsPanel panel = new GenomeSettingsPanel(neat);

        JDialog d = new JDialog(mainFrame, true);
        d.add(panel);
        d.setSize(400, 500);

        panel.setCloseListener(d::dispose);

        d.setVisible(true);
    }

    @Override
    public void onSpeciesSettings() {
        SpeciesSettingsPanel panel = new SpeciesSettingsPanel(neat);

        JDialog d = new JDialog(mainFrame, true);
        d.add(panel);
        d.setSize(400, 500);

        panel.setCloseListener(d::dispose);

        d.setVisible(true);
    }
}
