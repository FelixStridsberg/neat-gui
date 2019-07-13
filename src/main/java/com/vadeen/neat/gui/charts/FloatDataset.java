package com.vadeen.neat.gui.charts;

import org.jetbrains.annotations.NotNull;

public class FloatDataset extends Dataset<Float> {

    private float maxValue = 0;

    @Override
    public void add(@NotNull Float value) {
        if (value > maxValue)
            maxValue = value;

        super.add(value);
    }

    public float getMaxValue() {
        return maxValue;
    }
}
