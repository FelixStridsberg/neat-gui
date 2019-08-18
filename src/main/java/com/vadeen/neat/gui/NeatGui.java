package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.controller.EvolutionController;
import com.vadeen.neat.gui.controller.FileController;
import com.vadeen.neat.gui.controller.SettingsController;
import com.vadeen.neat.gui.controller.VisualizeController;
import com.vadeen.neat.gui.listeners.EvolveListener;
import com.vadeen.neat.gui.listeners.ExitListener;
import com.vadeen.neat.gui.listeners.NeatLoadListener;
import com.vadeen.neat.gui.menus.FileMenu;
import com.vadeen.neat.gui.menus.SettingsMenu;
import com.vadeen.neat.gui.panels.ControlPanel;
import com.vadeen.neat.gui.panels.MainPanel;
import com.vadeen.neat.gui.panels.StatsPanel;
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

    private final FileController fileController;
    private final SettingsController settingsController;
    private final EvolutionController evolutionController;
    private final VisualizeController visualizeController;

    private Neat neat;

    public NeatGui(Neat neat, Visualizer visualizer, VisualPanel vp) {
        this.neat = neat;

        this.fileController = new FileController(mainFrame, neat);
        this.settingsController = new SettingsController(mainFrame, neat);
        this.visualizeController = new VisualizeController(visualizer, vp, neat);
        this.evolutionController = new EvolutionController(neat);

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
        statsPanel.addGeneration(neat.getGeneration());
    }

    @Override
    public void onLoad(Neat neat) {
        this.neat = neat;

        fileController.setNeat(neat);
        settingsController.setNeat(neat);
        evolutionController.setNeat(neat);
        visualizeController.setNeat(neat);

        statsPanel.addGeneration(neat.getGeneration());
    }

    @Override
    public void onExit() {
        // Just halt for now... Yolo!
        System.exit(0);
    }
}
