package com.vadeen.neat.gui.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class IntValueChooser extends JPanel implements DocumentListener {

    private final IntTextField field = new IntTextField();

    private IntListener listener;

    public IntValueChooser(String label) {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(label));

        field.getDocument().addDocumentListener(this);
        add(field, BorderLayout.CENTER);
    }

    public void setValue(int value) {
        field.setValue(value);
    }

    public void setListener(IntListener listener) {
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
