package com.vadeen.neat.gui;

import com.vadeen.neat.generation.Generation;

public interface ControlListener {

    void onEvolve();

    void onRun();

    void onPause();

    void onSave();

    void onLoad();

    void onStartVisualization(Generation generation);

    void onStopVisualization();
}
