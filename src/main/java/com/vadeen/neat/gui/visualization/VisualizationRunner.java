package com.vadeen.neat.gui.visualization;

import com.vadeen.neat.generation.Generation;

import java.awt.*;

public class VisualizationRunner {

    private final Visualizer visualizer;
    private final VisualPanel vp;
    private Thread thread;

    private volatile boolean run = false;

    public VisualizationRunner(Visualizer visualizer, VisualPanel vp) {
        this.visualizer = visualizer;
        this.vp = vp;
    }

    public void run(Generation generation) {
        stop();

        visualizer.setup(generation);
        final int fpm = visualizer.getFramesPerSecond();
        final long frameDelay = 1000000000 / fpm;

        thread = new Thread(() -> {
            while (run) {
                long tickStart = System.nanoTime();

                if (!visualizer.tick())
                    break;

                vp.repaint();
                Toolkit.getDefaultToolkit().sync();

                long tickTime = System.nanoTime() - tickStart;
                long wait = (frameDelay - tickTime) / 1000000;

                try {
                    Thread.sleep(Math.max(wait, 0));
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        run = true;
        thread.start();
    }

    public void stop() {
        if (thread == null || !thread.isAlive())
            return;

        run = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread = null;
    }
}
