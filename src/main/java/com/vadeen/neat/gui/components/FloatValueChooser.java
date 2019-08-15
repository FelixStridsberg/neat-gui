package com.vadeen.neat.gui.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class FloatValueChooser extends JPanel implements DocumentListener {

    private final FloatTextField field = new FloatTextField();

    private FloatListener listener;

    public FloatValueChooser(String label) {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(label));

        field.getDocument().addDocumentListener(this);
        add(field, BorderLayout.CENTER);
    }

    public void setValue(float value) {
        field.setValue(value);
    }

    public void setListener(FloatListener listener) {
        this.listener = listener;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (listener != null)
            listener.onChange(field.getValue());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (listener != null)
            listener.onChange(field.getValue());
    }

    @Override
    public void changedUpdate(DocumentEvent e) { }
}
