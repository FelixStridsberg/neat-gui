package com.vadeen.neat.gui.visualization;

import javax.swing.*;
import java.awt.*;

/**
 * The Visual panel contains visual representation of what ever is being evolved.
 * For example if you evolve race genomes, this panel contains the cars racing.
 */
public abstract class VisualPanel extends JPanel {

    abstract public float getAspectRatio();

    @Override
    public Dimension getPreferredSize() {
        float aspect = getAspectRatio();

        int parentWidth = getParent().getWidth();
        int parentHeight = getParent().getHeight();

        int width = parentWidth;
        int height = (int)(parentWidth/aspect);

        if (height > parentHeight) {
            width = (int)(parentHeight*aspect);
            height = parentHeight;
        }

        System.out.println(width + ": " + height);
        return new Dimension(width, height);
    }
}
