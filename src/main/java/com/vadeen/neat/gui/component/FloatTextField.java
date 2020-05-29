package com.vadeen.neat.gui.component;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class FloatTextField extends JTextField implements DocumentListener {

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

    private FloatListener listener;

    public FloatTextField() {
        super();
        getDocument().addDocumentListener(this);
    }

    public void setListener(FloatListener listener) {
        this.listener = listener;
    }

    public float getValue() {
        String text = getText();
        if (text.length() == 0)
            return 0;

        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void setValue(float value) {
        setText(String.valueOf(value));
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (listener != null)
            listener.onChange(getValue());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (listener != null)
            listener.onChange(getValue());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {}

    @Override
    protected Document createDefaultModel() {
        return new NumberDocument();
    }
}
