package com.vadeen.neat.gui.menus;

import com.vadeen.neat.gui.controller.SettingsController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JMenu implements ActionListener {
    private static final String ACTION_NETWORK_SETTINGS = "network_settings";

    private final SettingsController settingsController;

    public SettingsMenu(SettingsController settingsController) {
        super("Settings");
        this.settingsController = settingsController;

        JMenuItem mutationSettings = new JMenuItem("Network settings...");
        mutationSettings.setActionCommand(ACTION_NETWORK_SETTINGS);
        add(mutationSettings);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_NETWORK_SETTINGS:
                settingsController.openNetworkSettings();
                break;
        }
    }

    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItem.addActionListener(this);
        return super.add(menuItem);
    }
}
