package com.larregle;

import com.larregle.engine.GameCanvas;
import com.larregle.engine.TicTacToeGame;

import javax.swing.*;

public class App {
    public static void main(String... args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        GameCanvas gameCanvas = new GameCanvas(ticTacToeGame);
        JFrame frame = new JFrame();
        frame.setTitle("TIC TAC TOE");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(gameCanvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gameCanvas.requestFocus();
        gameCanvas.start();
    }
}
