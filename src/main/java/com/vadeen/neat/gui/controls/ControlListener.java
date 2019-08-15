package com.vadeen.neat.gui.controls;

import com.vadeen.neat.generation.Generation;

public interface ControlListener {

    void onEvolve();

    void onRun();

    void onPause();

    void onStartVisualization(Generation generation);

    void onStopVisualization();
}