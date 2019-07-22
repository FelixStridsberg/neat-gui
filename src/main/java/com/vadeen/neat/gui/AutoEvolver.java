package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.generation.Generation;

public class AutoEvolver extends Thread {
    private final Neat neat;
    private final StatsPanel visualPanel;

    private volatile boolean running = true;

    public AutoEvolver(Neat neat, StatsPanel visualPanel) {
        this.neat = neat;
        this.visualPanel = visualPanel;
    }

    @Override
    public void run() {
        while (running) {
            Generation g = neat.evolve();
            visualPanel.addGeneration(g);
        }
    }

    public void halt() {
        running = false;
    }
}
