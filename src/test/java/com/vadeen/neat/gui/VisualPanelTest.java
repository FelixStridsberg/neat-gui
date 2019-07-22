package com.vadeen.neat.gui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vadeen.neat.gene.GeneFactory;
import com.vadeen.neat.io.NeatIO;
import com.vadeen.neat.io.json.generation.GenerationJson;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class VisualPanelTest {

    private static JFrame frame = new JFrame("gui-test");

    public static void main(String[] args) throws IOException {
        String filename = NeatIO.class.getClassLoader().getResource("generations/generations.json").getFile();
        File file = new File(filename);

        ObjectMapper mapper = new ObjectMapper();
        List<GenerationJson> generations = mapper.readValue(file, new TypeReference<List<GenerationJson>>(){});

        StatsPanel vp = new StatsPanel();
        GeneFactory geneFactory = new GeneFactory();

        new Thread(() -> {
            for (GenerationJson json : generations) {
                vp.addGeneration(json.toGeneration(geneFactory));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        JPanel panel2 = new JPanel();
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(vp);
        panel.add(panel2);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 350, 800);
        frame.add(panel);
        frame.setVisible(true);
    }
}