package com.vadeen.neat.gui.menus;

import com.vadeen.neat.gui.controls.SettingsMenuListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO fix, clumsy.
public class SettingsMenu extends JMenu implements ActionListener {
    private static final String ACTION_MUTATION_SETTINGS = "network_mutation_settings";
    private static final String ACTION_GENERATION_SETTINGS = "network_generation_settings";
    private static final String ACTION_GENOME_SETTINGS = "network_genome_settings";
    private static final String ACTION_SPECIES_SETTINGS = "network_species_settings";

    private SettingsMenuListener listener = null;

    public SettingsMenu() {
        super("Settings");

        JMenuItem mutationSettings = new JMenuItem("Mutation settings...");
        mutationSettings.setActionCommand(ACTION_MUTATION_SETTINGS);
        add(mutationSettings);

        JMenuItem generationSettings = new JMenuItem("Generation settings...");
        generationSettings.setActionCommand(ACTION_GENERATION_SETTINGS);
        add(generationSettings);

        JMenuItem genomeSettings = new JMenuItem("Genome settings...");
        genomeSettings.setActionCommand(ACTION_GENOME_SETTINGS);
        add(genomeSettings);

        JMenuItem speciesSettings = new JMenuItem("Species settings...");
        speciesSettings.setActionCommand(ACTION_SPECIES_SETTINGS);
        add(speciesSettings);
    }

    public void addListener(SettingsMenuListener listener) {
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
            case ACTION_GENERATION_SETTINGS:
                listener.onGenerationSettings();
                break;
            case ACTION_GENOME_SETTINGS:
                listener.onGenomeSettings();
                break;
            case ACTION_SPECIES_SETTINGS:
                listener.onSpeciesSettings();
                break;
        }
    }

    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItem.addActionListener(this);
        return super.add(menuItem);
    }
}