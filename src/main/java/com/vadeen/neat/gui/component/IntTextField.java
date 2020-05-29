package com.vadeen.neat.gui.component;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntTextField extends JTextField implements DocumentListener {

    private static class NumberDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // Only allow numbers.
            str = str.replaceAll("[^\\d]", "");
            super.insertString(offs, str, a);
        }
    }

    private IntListener listener;

    public IntTextField() {
        super();
        getDocument().addDocumentListener(this);
    }

    public void setListener(IntListener listener) {
        this.listener = listener;
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
