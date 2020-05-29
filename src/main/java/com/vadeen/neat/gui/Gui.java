package com.vadeen.neat.gui;

import java.awt.*;

public class Gui {

    /**
     * Returns a semi unique color for the provided id.
     */
    public static Color colorOfId(int id) {
        return Color.getHSBColor((id/20.0f)%1.0f, 1.0f - (id/20.0f)%0.5f, 1.0f - (id/20.0f)%0.5f);
    }
}
