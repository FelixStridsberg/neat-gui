package com.vadeen.neat.gui.visualization;

import com.vadeen.neat.generation.Generation;

public interface Visualizer {

    /**
     * @return Desired frames per second.
     */
    int getFramesPerSecond();

    /**
     * Called before the run of a visualization session.
     *
     * @param generation Generation to be visualized.
     */
    void setup(Generation generation);

    /**
     * Ticks visualization.
     *
     * @return True if the visualization should continue. False if it should stop.
     */
    boolean tick();
}
