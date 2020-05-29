package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;

/**
 * Contains everything required for the simulation and visualisation.
 */
public class SimulationContext {

    private Neat neat;

    public SimulationContext(Neat neat) {
        this.neat = neat;
    }

    public void setNeat(Neat neat) {
        this.neat = neat;
    }

    public Neat getNeat() {
        return neat;
    }
}
