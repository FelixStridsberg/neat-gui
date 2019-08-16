package com.vadeen.neat.gui.menus;

import com.vadeen.neat.gui.controller.SettingsController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JMenu implements ActionListener {
    private static final String ACTION_MUTATION_SETTINGS = "network_mutation_settings";
    private static final String ACTION_GENERATION_SETTINGS = "network_generation_settings";
    private static final String ACTION_GENOME_SETTINGS = "network_genome_settings";
    private static final String ACTION_SPECIES_SETTINGS = "network_species_settings";

    private final SettingsController settingsController;

    public SettingsMenu(SettingsController settingsController) {
        super("Settings");
        this.settingsController = settingsController;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_MUTATION_SETTINGS:
                settingsController.openMutatorSettings();
                break;
            case ACTION_GENERATION_SETTINGS:
                settingsController.openGenerationSettings();
                break;
            case ACTION_GENOME_SETTINGS:
                settingsController.openGenomeSettings();
                break;
            case ACTION_SPECIES_SETTINGS:
                settingsController.openSpeciesSettings();
                break;
        }
    }

    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItem.addActionListener(this);
        return super.add(menuItem);
    }
}
