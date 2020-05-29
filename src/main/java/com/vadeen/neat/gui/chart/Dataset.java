package com.vadeen.neat.gui.chart;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dataset<T> {

    private LinkedList<T> values = new LinkedList<>();

    public void add(T value) {
        // TODO fix in line chart, when going over this it's just a vertical line.
        if (values.size() > 138)
            values.pop();

        values.add(value);
    }

    public int size() {
        return values.size();
    }

    public List<T> getValues() {
        return Collections.unmodifiableList(values);
    }
}
