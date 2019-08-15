package com.vadeen.neat.gui.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JPanel implements ActionListener {
    protected static String DIALOG_CLOSE_ACTION = "close_settings_dialog";

    protected CloseListener closeListener;

    public SettingsDialog() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DIALOG_CLOSE_ACTION) && closeListener != null)
            closeListener.close();
    }

    protected void initButtons() {
        JButton doneButton = new JButton("Done");
        doneButton.setActionCommand(DIALOG_CLOSE_ACTION);
        doneButton.addActionListener(this);
        add(doneButton);
    }
}
