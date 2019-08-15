package com.vadeen.neat.gui.components;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class FloatTextField extends JTextField {

    private static class NumberDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

            // Only allow numbers and period.
            str = str.replaceAll("[^\\d\\.]", "");

            // Only allow period if there is none already.
            Content content = getContent();
            String value = content.getString(0, content.length());
            if (value.contains(".")) {
                str = str.replace(".", "");
            }

            super.insertString(offs, str, a);
        }
    }

    @Override
    protected Document createDefaultModel() {
        return new NumberDocument();
    }

    public float getValue() {
        try {
            return Float.parseFloat(getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void setValue(float value) {
        setText(String.valueOf(value));
    }
}
