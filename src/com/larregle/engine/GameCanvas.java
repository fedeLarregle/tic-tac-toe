package com.larregle.engine;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {

    public static final int WIDTH = 440;
    public static final int HEIGHT = 440;

    private final TicTacToeGame game;
    private BufferStrategy bufferStrategy;
    private boolean isRunning;

    public GameCanvas(TicTacToeGame game) {
        this.game = game;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(MouseHandler.getInstance());
    }

    public void start() {
        if (!isRunning) {
            createBufferStrategy(3);
            bufferStrategy = getBufferStrategy();
            isRunning = true;
            new Thread(new GameLoop(this)).start();
        }
    }

    public class GameLoop implements Runnable {

        private static final int FPS = 60;
        private GameCanvas gameCanvas;

        public GameLoop(GameCanvas gameCanvas) {
            this.gameCanvas = gameCanvas;
        }

        @Override
        public void run() {
            long startTime;
            long waitTime;
            long URTimeMillis;
            long targetTime = 1_000 / FPS; // Time that should take per frame

            /* GAME LOOP */
            while(gameCanvas.isRunning) {

                startTime = System.nanoTime();
                URTimeMillis = (System.nanoTime() - startTime) / 1_000_000;
                waitTime = targetTime - URTimeMillis; // Checking if took longer than expected

                if(waitTime > 0) {
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }

            }
        }
    }
}
