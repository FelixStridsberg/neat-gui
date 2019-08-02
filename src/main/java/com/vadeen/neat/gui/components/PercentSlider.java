package com.vadeen.neat.gui.components;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PercentSlider extends JPanel implements ChangeListener {

    private JSlider slider = new JSlider();
    private JLabel valueLabel = new JLabel();

    private FloatListener listener;

    public PercentSlider(String label) {
        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(label));

        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(1);
        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);

        add(slider, BorderLayout.CENTER);
        add(valueLabel, BorderLayout.LINE_END);

        updateLabel();
    }

    public void setListener(FloatListener listener) {
        this.listener = listener;
    }

    public void setValue(float value) {
        slider.setValue((int)(value*100));
        updateLabel();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        updateLabel();

        int intValue = slider.getValue();
        float value = intValue / 100.0f;

        if (listener != null)
            listener.onChange(value);
    }

    private void updateLabel() {
        int intValue = slider.getValue();
        valueLabel.setText(intValue + "%");
    }
}
