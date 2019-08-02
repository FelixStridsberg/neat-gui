package com.vadeen.neat.gui.components;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

public class FloatValueChooser extends JPanel implements PropertyChangeListener {

    JFormattedTextField field = new JFormattedTextField(DecimalFormat.getInstance());

    private FloatListener listener;

    public FloatValueChooser(String label) {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(label));

        field.addPropertyChangeListener("value", this);
        add(field, BorderLayout.CENTER);
    }

    public void setValue(float value) {
        field.setValue((double)value);
    }

    public void setListener(FloatListener listener) {
        this.listener = listener;
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Double value = (Double)field.getValue();

        if (value == null)
            value = 0.0;

        if (listener != null)
            listener.onChange(value.floatValue());
    }
}
