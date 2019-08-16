package com.vadeen.neat.gui.menus;

import com.vadeen.neat.Neat;
import com.vadeen.neat.gui.panels.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JMenu implements ActionListener {
    private static final String ACTION_MUTATION_SETTINGS = "network_mutation_settings";
    private static final String ACTION_GENERATION_SETTINGS = "network_generation_settings";
    private static final String ACTION_GENOME_SETTINGS = "network_genome_settings";
    private static final String ACTION_SPECIES_SETTINGS = "network_species_settings";

    private Neat neat;

    public SettingsMenu(Neat neat) {
        super("Settings");
        this.neat = neat;

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

    public void setNeat(Neat neat) {
        this.neat = neat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_MUTATION_SETTINGS:
                openDialog(new MutatorSettingsPanel(neat));
                break;
            case ACTION_GENERATION_SETTINGS:
                openDialog(new GenerationSettingsPanel(neat));
                break;
            case ACTION_GENOME_SETTINGS:
                openDialog(new GenomeSettingsPanel(neat));
                break;
            case ACTION_SPECIES_SETTINGS:
                openDialog(new SpeciesSettingsPanel(neat));
                break;
        }
    }

    @Override
    public JMenuItem add(JMenuItem menuItem) {
        menuItem.addActionListener(this);
        return super.add(menuItem);
    }


    private void openDialog(SettingsDialog dialog) {
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JDialog d = new JDialog(mainFrame, true);
        dialog.setCloseListener(d::dispose);

        d.add(dialog);
        d.setSize(400, 500);
        d.setVisible(true);
    }
}
