package com.vadeen.neat.gui.panel;

import com.vadeen.neat.generation.Generation;
import com.vadeen.neat.gui.chart.FloatDataset;
import com.vadeen.neat.gui.chart.LineChart;
import com.vadeen.neat.gui.chart.SpeciesProportionChart;
import com.vadeen.neat.gui.stats.NeatStats;
import com.vadeen.neat.gui.stats.info.SpeciesInfo;
import com.vadeen.neat.species.Species;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stats panel contains graphs and stats of the network.
 */
public class StatsPanel extends JPanel {

    private final FloatDataset fitnessData = new FloatDataset();

    private final NeatStats stats = new NeatStats();
    private final SpeciesProportionChart speciesPanel = new SpeciesProportionChart();
    private final GenomePanel genomePanel = new GenomePanel(null);
    private final InfoPanel infoPanel = new InfoPanel();


    public StatsPanel() {
        LineChart fitnessPanel = new LineChart(fitnessData);

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 2));
        top.add(infoPanel);
        top.add(genomePanel);

        JPanel bottom = new JPanel();
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, getParent().getHeight());
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(400, getParent().getHeight());
    }
}
