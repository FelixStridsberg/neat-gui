package com.vadeen.neat.gui.listeners;

import com.vadeen.neat.generation.Generation;

public interface ControlListener {

    void onStartVisualization(Generation generation);

    void onStopVisualization();
}
