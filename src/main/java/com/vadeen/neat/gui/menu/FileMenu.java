package com.vadeen.neat.gui.menu;

import com.vadeen.neat.gui.controller.FileController;
import com.vadeen.neat.gui.listener.ExitListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu implements ActionListener {
    private static final String ACTION_LOAD_NETWORK = "load_network";
    private static final String ACTION_SAVE_NETWORK = "save_network";
    private static final String ACTION_EXIT = "exit";

    private final FileController fileController;

    private ExitListener listener = null;

    public FileMenu(FileController fileController) {
        super("File");
        this.fileController = fileController;

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

    public void addListener(ExitListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener == null)
            return;

        switch (e.getActionCommand()) {
            case ACTION_LOAD_NETWORK:
                fileController.load();
                break;
            case ACTION_SAVE_NETWORK:
                fileController.save();
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
