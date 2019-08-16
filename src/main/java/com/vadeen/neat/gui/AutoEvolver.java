package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.listeners.EvolveListener;

public class AutoEvolver extends Thread {
    private final Neat neat;
    private EvolveListener evolveListener;

    private volatile boolean running = true;

    public AutoEvolver(Neat neat) {
        this.neat = neat;
    }

    public void setEvolveListener(EvolveListener evolveListener) {
        this.evolveListener = evolveListener;
    }

    @Override
    public void run() {
        while (running) {
            neat.evolve();

            if (evolveListener != null)
                evolveListener.onEvolve();
        }
    }

    public void halt() {
        running = false;
    }
}
