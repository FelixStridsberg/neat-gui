package com.vadeen.neat.gui.charts;


import com.vadeen.neat.gui.Gui;
import com.vadeen.neat.gui.stats.info.SpeciesInfo;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple auto scaling area chart to show the proportion between species.
 */
public class SpeciesProportionChart extends JPanel {

    // When this is is reached, the oldest sample is removed when adding a new sample.
    private static final int MAX_SAMPLES = 30;

    private final LinkedList<List<SpeciesInfo>> speciesInfo = new LinkedList<>();

    public void addInfo(List<SpeciesInfo> speciesInfo) {
        this.speciesInfo.add(speciesInfo);

        if (this.speciesInfo.size() > MAX_SAMPLES)
            this.speciesInfo.remove();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        paintAreaChart(g2);
    }

    private void paintAreaChart(Graphics2D g2) {
        // Visual eight of each data sample.
        int segmentHeight = Math.round(getHeight()/(float)speciesInfo.size());

        int sampleIndex = 0;
        for (List<SpeciesInfo> speciesInfos : speciesInfo) {
            // Total sum of all species sizes.
            int total = speciesInfos.stream().mapToInt(SpeciesInfo::getSize).sum();

            // Sort on id so we get the species on the same place every time.
            speciesInfos.sort(Comparator.comparing(SpeciesInfo::getId));

            // Pain each species.
            int consumed = 0;
            for (int i = 0; i < speciesInfos.size(); i++) {
                SpeciesInfo si = speciesInfos.get(i);
                g2.setColor(Gui.colorOfId(si.getId()));

                int x = (int)(((float)(total - consumed)/total)*getWidth());
                int y = getHeight() - segmentHeight*sampleIndex - segmentHeight;

                g2.fillRect(0, y, x, segmentHeight);

                consumed += si.getSize();
            }

            sampleIndex++;
        }
    }
}
