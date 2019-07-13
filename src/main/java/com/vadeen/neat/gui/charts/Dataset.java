package com.vadeen.neat.gui.charts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dataset<T> {

    private List<T> values = new ArrayList<>();

    public void add(T value) {
        values.add(value);
    }

    public int size() {
        return values.size();
    }

    public List<T> getValues() {
        return Collections.unmodifiableList(values);
    }
}
