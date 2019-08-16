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
import com.vadeen.neat.gui.panels.StatsPanel;
import com.vadeen.neat.gui.visualization.VisualPanel;
import com.vadeen.neat.gui.visualization.Visualizer;

import javax.swing.*;
import java.awt.*;

/**
 * NeatGui is a graphical user interface that takes the control of a Neat object and lets you push buttons and slide
 * sliders to manipulate to interactively evolve the network.
 */
public class NeatGui implements ExitListener, NeatLoadListener, EvolveListener {

    private final JFrame mainFrame = new JFrame("NEAT gui");

    private final JMenuBar menuBar = new JMenuBar();
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final StatsPanel visualPanel = new StatsPanel();

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
        initPanels(vp);
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

    private void initPanels(VisualPanel vp) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridBagLayout());
        wrapper.add(vp);

        ControlPanel controlPanel = new ControlPanel(evolutionController, visualizeController);

        mainPanel.add(wrapper, BorderLayout.CENTER);
        mainPanel.add(visualPanel, BorderLayout.LINE_END);
        mainPanel.add(controlPanel, BorderLayout.PAGE_START);


        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 680);
        mainFrame.add(mainPanel);
    }

    @Override
    public void onEvolve() {
        visualPanel.addGeneration(neat.getGeneration());
    }

    @Override
    public void onLoad(Neat neat) {
        this.neat = neat;

        fileController.setNeat(neat);
        settingsController.setNeat(neat);
        evolutionController.setNeat(neat);
        visualizeController.setNeat(neat);

        visualPanel.addGeneration(neat.getGeneration());
    }

    @Override
    public void onExit() {
        // Just halt for now... Yolo!
        System.exit(0);
    }
}
