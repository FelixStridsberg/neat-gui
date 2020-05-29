package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.controller.EvolutionController;
import com.vadeen.neat.gui.controller.FileController;
import com.vadeen.neat.gui.controller.SettingsController;
import com.vadeen.neat.gui.controller.VisualizeController;
import com.vadeen.neat.gui.listener.EvolveListener;
import com.vadeen.neat.gui.listener.ExitListener;
import com.vadeen.neat.gui.listener.NeatLoadListener;
import com.vadeen.neat.gui.menu.FileMenu;
import com.vadeen.neat.gui.menu.SettingsMenu;
import com.vadeen.neat.gui.panel.ControlPanel;
import com.vadeen.neat.gui.panel.MainPanel;
import com.vadeen.neat.gui.panel.StatsPanel;
import com.vadeen.neat.gui.visualization.VisualPanel;
import com.vadeen.neat.gui.visualization.Visualizer;

import javax.swing.*;

/**
 * NeatGui is a graphical user interface that takes the control of a Neat object and lets you push buttons and slide
 * sliders to manipulate to interactively evolve the network.
 */
public class NeatGui implements ExitListener, NeatLoadListener, EvolveListener {

    private final JFrame mainFrame = new JFrame("NEAT gui");

    private final JMenuBar menuBar = new JMenuBar();
    private final StatsPanel statsPanel = new StatsPanel();

    private final SimulationContext context;
    private final FileController fileController;
    private final SettingsController settingsController;
    private final EvolutionController evolutionController;
    private final VisualizeController visualizeController;

    public NeatGui(Neat neat, Visualizer visualizer, VisualPanel vp) {
        this.context = new SimulationContext(neat);
        this.fileController = new FileController(mainFrame, context);
        this.settingsController = new SettingsController(mainFrame, context);
        this.visualizeController = new VisualizeController(visualizer, vp, context);
        this.evolutionController = new EvolutionController(context);

        this.fileController.setLoadListener(this);
        this.evolutionController.setEvolveListener(this);

        initMenus();

        JPanel controlPanel = ControlPanel.create(evolutionController, visualizeController);
        mainFrame.setContentPane(MainPanel.create(controlPanel, statsPanel, vp));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 680);
    }

    public void run() {
        mainFrame.setVisible(true);
    }

    private void initMenus() {
        SettingsMenu settingsMenu = new SettingsMenu(settingsController);

        FileMenu fileMenu = new FileMenu(fileController);
        fileMenu.addListener(this);

        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);

        mainFrame.setJMenuBar(menuBar);
    }

    @Override
    public void onEvolve() {
        statsPanel.addGeneration(context.getNeat().getGeneration());
    }

    @Override
    public void onLoad(Neat neat) {
        context.setNeat(neat);

        statsPanel.addGeneration(neat.getGeneration());
    }

    @Override
    public void onExit() {
        // Just halt for now... Yolo!
        System.exit(0);
    }
}
