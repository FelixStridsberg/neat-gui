package com.vadeen.neat.gui.controller;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.AutoEvolver;
import com.vadeen.neat.gui.listener.EvolveListener;

public class EvolutionController {

    private Neat neat;
    private EvolveListener evolveListener;
    private AutoEvolver autoEvolver;

    public EvolutionController(Neat neat) {
        this.neat = neat;
    }

    public void setNeat(Neat neat) {
        this.neat = neat;
    }

    public void setEvolveListener(EvolveListener evolveListener) {
        this.evolveListener = evolveListener;
    }

    public void evolve() {
        neat.evolve();

        if (evolveListener != null)
            evolveListener.onEvolve();
    }

    public void run() {
        if (autoEvolver != null) {
            stop();
        }

        autoEvolver = new AutoEvolver(neat);
        autoEvolver.setEvolveListener(evolveListener);
        autoEvolver.start();
    }

    public void stop() {
        autoEvolver.halt();
        try {
            autoEvolver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        autoEvolver = null;
    }
}
