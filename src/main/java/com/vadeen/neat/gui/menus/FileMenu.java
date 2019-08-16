package com.vadeen.neat.gui.menus;

import com.vadeen.neat.gui.listeners.FileMenuListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu implements ActionListener {
    private static final String ACTION_LOAD_NETWORK = "load_network";
    private static final String ACTION_SAVE_NETWORK = "save_network";
    private static final String ACTION_EXIT = "exit";

    private FileMenuListener listener = null;

    public FileMenu() {
        super("File");

        JMenuItem loadNetwork = new JMenuItem("Load network...");
        loadNetwork.setActionCommand(ACTION_LOAD_NETWORK);
        add(loadNetwork);

        JMenuItem saveNetwork = new JMenuItem("Save network...");
        saveNetwork.setActionCommand(ACTION_SAVE_NETWORK);
        add(saveNetwork);

        addSeparator();

        JMenuItem exit = new JMenuItem("Exit");
        exit.setActionCommand(ACTION_EXIT);
        add(exit);
    }

    public void addListener(FileMenuListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener == null)
            return;

        switch (e.getActionCommand()) {
            case ACTION_LOAD_NETWORK:
                listener.onLoadNetwork();
                break;
            case ACTION_SAVE_NETWORK:
                listener.onSaveNetwork();
                break;
            case ACTION_EXIT:
                listener.onExit();
                break;
        }

    }

    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItem.addActionListener(this);
        return super.add(menuItem);
    }
}
