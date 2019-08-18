package com.vadeen.neat.gui;

import com.vadeen.neat.Neat;
import com.vadeen.neat.evaluators.SimpleEvaluator;
import com.vadeen.neat.genome.Genome;
import com.vadeen.neat.genome.GenomeEvaluator;
import com.vadeen.neat.gui.visualization.VisualPanel;

import javax.swing.*;
import java.awt.*;

public class NeatGuiTest {

    public static void main(String[] args) {
        // More connections, more better.
        GenomeEvaluator evaluator = new SimpleEvaluator() {
            @Override
            public float evaluate(Genome genome) {
                return genome.getConnections().size();
            }
        };

        Neat neat = Neat.create(evaluator, 2, 2);

        VisualPanel vp = new VisualPanel() {
            @Override
            public float getAspectRatio() {
                return 4f/3f;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        NeatGui gui = new NeatGui(neat, null, vp);

        gui.run();
    }

}