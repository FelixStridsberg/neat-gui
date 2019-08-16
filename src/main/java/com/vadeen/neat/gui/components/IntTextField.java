package com.vadeen.neat.gui.components;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntTextField extends JTextField {

    private static class NumberDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // Only allow numbers.
            str = str.replaceAll("[^\\d]", "");
            super.insertString(offs, str, a);
        }
    }

    @Override
    protected Document createDefaultModel() {
        return new NumberDocument();
    }

    public int getValue() {
        String text = getText();
        if (text.length() == 0)
            return 0;

        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void setValue(int value) {
        setText(String.valueOf(value));
    }
}
