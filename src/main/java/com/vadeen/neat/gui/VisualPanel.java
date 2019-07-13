package com.vadeen.neat.gui;

import com.vadeen.neat.generation.Generation;
import com.vadeen.neat.gui.charts.FloatDataset;
import com.vadeen.neat.gui.charts.LineChart;
import com.vadeen.neat.gui.charts.SpeciesProportionChart;
import com.vadeen.neat.gui.stats.NeatStats;
import com.vadeen.neat.gui.stats.info.SpeciesInfo;
import com.vadeen.neat.species.Species;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VisualPanel extends JPanel {

    private final FloatDataset fitnessData = new FloatDataset();
    private final LineChart fitnessPanel = new LineChart(fitnessData);

    private final NeatStats stats = new NeatStats();
    private final SpeciesProportionChart speciesPanel = new SpeciesProportionChart();
    private final GenomePanel genomePanel = new GenomePanel(null);
    private final InfoPanel infoPanel = new InfoPanel();
    private final JPanel top = new JPanel();
    private final JPanel bottom = new JPanel();

    public VisualPanel() {
        top.setLayout(new GridLayout(1, 2));
        top.add(infoPanel);
        top.add(genomePanel);

        bottom.setLayout(new GridLayout(1, 2));
        bottom.add(fitnessPanel);
        bottom.add(speciesPanel);

        setLayout(new GridLayout(2, 1));
        add(top);
        add(bottom);
    }

    public void addGeneration(Generation g) {
        List<SpeciesInfo> ss = new ArrayList<>();
        for (Species s : g.getSpecies())
            ss.add(SpeciesInfo.of(s));

        fitnessData.add(g.getBestGenome().getFitness());
        speciesPanel.addInfo(ss);
        stats.addGeneration(g);
        genomePanel.setGenome(g.getBestGenome());
        infoPanel.setGeneration(g);
        repaint();
    }
}
