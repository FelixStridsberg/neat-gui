package com.vadeen.neat.gui.menus;

import com.vadeen.neat.gui.controls.NetworkMenuListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetworkMenu extends JMenu implements ActionListener {
    private static final String ACTION_MUTATION_SETTINGS = "network_mutation_settings";

    private NetworkMenuListener listener = null;

    public NetworkMenu() {
        super("Network");

        JMenuItem mutationSettings = new JMenuItem("Mutation settings...");
        mutationSettings.setActionCommand(ACTION_MUTATION_SETTINGS);
        add(mutationSettings);
    }

    public void addListener(NetworkMenuListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener == null)
            return;

        switch (e.getActionCommand()) {
            case ACTION_MUTATION_SETTINGS:
                listener.onMutationSettings();
                break;
        }
    }

    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItem.addActionListener(this);
        return super.add(menuItem);
    }
}
