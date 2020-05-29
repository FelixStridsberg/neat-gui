package com.vadeen.neat.gui.stats;

import com.vadeen.neat.generation.Generation;
import com.vadeen.neat.gui.stats.info.GenomeInfo;
import com.vadeen.neat.gui.stats.info.SpeciesInfo;
import com.vadeen.neat.species.Species;

import java.util.ArrayList;
import java.util.List;

/**
 * Gathers info about generations over time.
 */
public class NeatStats {

    /**
     * Species stats over time.
     */
    public final List<List<SpeciesInfo>> speciesOverTime = new ArrayList<>();

    /**
     * Best genome stats over time.
     */
    public final List<GenomeInfo> bestGenomeOverTime = new ArrayList<>();

    public void addGeneration(Generation g) {
        addSpecies(g.getSpecies());
        bestGenomeOverTime.add(GenomeInfo.of(g.getBestGenome()));
    }

    private void addSpecies(List<Species> species) {
        List<SpeciesInfo> stats = new ArrayList<>();
        for (Species s : species)
            stats.add(SpeciesInfo.of(s));

        speciesOverTime.add(stats);
    }

    public List<List<SpeciesInfo>> getSpeciesOverTime() {
        return speciesOverTime;
    }

    public List<GenomeInfo> getBestGenomeOverTime() {
        return bestGenomeOverTime;
    }
}
