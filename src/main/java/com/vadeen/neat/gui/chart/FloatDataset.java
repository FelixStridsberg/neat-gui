package com.vadeen.neat.gui.chart;

public class FloatDataset extends Dataset<Float> {

    private float maxValue = 0;

    @Override
    public void add(Float value) {
        if (value > maxValue)
            maxValue = value;

        super.add(value);
    }

    public float getMaxValue() {
        return maxValue;
    }
}
