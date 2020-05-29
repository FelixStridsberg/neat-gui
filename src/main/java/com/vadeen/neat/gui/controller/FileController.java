package com.vadeen.neat.gui.controller;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.listener.NeatLoadListener;
import com.vadeen.neat.io.NeatIO;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FileController {

    private final JFrame mainFrame;

    private Neat neat;
    private NeatLoadListener loadListener;

    public FileController(JFrame mainFrame, Neat neat) {
        this.mainFrame = mainFrame;
        this.neat = neat;
    }

    public void setNeat(Neat neat) {
        this.neat = neat;
    }

    public void setLoadListener(NeatLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void save() {
        try {
            File file = fileDialog("Specify a file to save");
            if (file == null)
                return;

            NeatIO.write(file, neat);
            System.out.println("Wrote to " + file + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            File file = fileDialog("Choose a file");
            if (file == null)
                return;

            Neat loadedNeat = NeatIO.readNeat(file, neat.getGenerationEvaluator().getEvaluator());

            if (loadListener != null)
                loadListener.onLoad(loadedNeat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File fileDialog(String title) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);

        int userSelection = fileChooser.showSaveDialog(mainFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return  null;
    }
}
