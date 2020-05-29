package com.vadeen.neat.gui.controller;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.AutoEvolver;
import com.vadeen.neat.gui.SimulationContext;
import com.vadeen.neat.gui.listener.EvolveListener;

public class EvolutionController {

    private final SimulationContext context;

    private EvolveListener evolveListener;
    private AutoEvolver autoEvolver;

    public EvolutionController(SimulationContext context) {
        this.context = context;
    }

    public void setEvolveListener(EvolveListener evolveListener) {
        this.evolveListener = evolveListener;
    }

    public void evolve() {
        context.getNeat().evolve();

        if (evolveListener != null)
            evolveListener.onEvolve();
    }

    public void run() {
        if (autoEvolver != null) {
            stop();
        }

        autoEvolver = new AutoEvolver(context.getNeat());
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
