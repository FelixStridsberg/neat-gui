package com.vadeen.neat.gui.components;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FloatValueChooser extends JPanel implements PropertyChangeListener {

    private final FloatTextField field = new FloatTextField();

    private FloatListener listener;

    public FloatValueChooser(String label) {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(label));

        field.addPropertyChangeListener("value", this);
        add(field, BorderLayout.CENTER);
    }

    public void setValue(float value) {
        field.setValue(value);
    }

    public void setListener(FloatListener listener) {
        this.listener = listener;
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (listener != null)
            listener.onChange(field.getValue());
    }
}
