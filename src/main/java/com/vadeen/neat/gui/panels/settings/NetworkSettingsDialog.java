package com.vadeen.neat.gui.panels.settings;

import com.vadeen.neat.Neat;

import javax.swing.*;

public class NetworkSettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel mutatorPanel;
    private JPanel speciesPanel;
    private JPanel genomePanel;
    private JPanel generationPanel;

    private final Neat neat;

    public static void open(JFrame owner, Neat neat) {
        NetworkSettingsDialog dialog = new NetworkSettingsDialog(owner, neat);
        dialog.pack();
        dialog.setVisible(true);
    }

    public NetworkSettingsDialog(JFrame owner, Neat neat) {
        super(owner, "Network settings", true);
        this.neat = neat;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> close());
    }

    private void close() {
        dispose();
    }

    private void createUIComponents() {
        mutatorPanel = new MutatorSettingsPanel(neat).getContentPane();
        speciesPanel = new SpeciesSettingsPanel(neat).getContentPane();
        genomePanel = new GenomeSettingsPanel(neat).getContentPane();
        generationPanel = new GenerationSettingsPanel(neat).getContentPane();
    }
}
