package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.controller.EvolutionController;
import com.vadeen.neat.gui.controller.VisualizeController;
import com.vadeen.neat.gui.listeners.EvolveListener;
import com.vadeen.neat.gui.listeners.FileMenuListener;
import com.vadeen.neat.gui.menus.FileMenu;
import com.vadeen.neat.gui.menus.SettingsMenu;
import com.vadeen.neat.gui.panels.ControlPanel;
import com.vadeen.neat.gui.panels.StatsPanel;
import com.vadeen.neat.gui.visualization.VisualPanel;
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
public class NeatGui implements FileMenuListener, EvolveListener {

    private final JFrame mainFrame = new JFrame("NEAT gui");

    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final StatsPanel visualPanel = new StatsPanel();

    private final JMenuBar menuBar = new JMenuBar();
    private final SettingsMenu settingsMenu;

    private final EvolutionController evolutionController;
    private final VisualizeController visualizeController;

    private Neat neat;

    public NeatGui(Neat neat, Visualizer visualizer, VisualPanel vp) {
        this.neat = neat;
        this.settingsMenu = new SettingsMenu(neat);

        this.evolutionController = new EvolutionController(neat);
        this.evolutionController.setEvolveListener(this);

        this.visualizeController = new VisualizeController(visualizer, vp, neat);

        initMenus();
        initPanels(vp);
    }

    public void run() {
        mainFrame.setVisible(true);
    }

    private void initMenus() {
        FileMenu fileMenu = new FileMenu();
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
                settingsMenu.setNeat(neat);
                evolutionController.setNeat(neat);
                visualizeController.setNeat(neat);

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
    public void onEvolve() {
        visualPanel.addGeneration(neat.getGeneration());
    }
}
