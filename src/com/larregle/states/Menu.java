package com.larregle.states;

import com.larregle.engine.KeyHandler;
import com.larregle.engine.TicTacToeGame;
import com.larregle.entities.Board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu {

    private final TicTacToeGame game;
    public static String[] options;
    public int currentOption;

    public Menu(TicTacToeGame game) {
        this.game = game;
        currentOption = 0;
        options = new String[]{"START", "QUIT"};
    }

    public void update() {
        if (KeyHandler.getInstance().wasKeyTyped(KeyHandler.ENTER)) {
            if (currentOption == 0) {
                game.setGameState(TicTacToeGame.GameState.GAME_RUNNING);
            }
        } else if (KeyHandler.getInstance().wasKeyTyped(KeyHandler.DOWN)) {
            if (currentOption == 1) {
                currentOption = 0;
            } else {
                currentOption++;
            }
        } else if (KeyHandler.getInstance().wasKeyTyped(KeyHandler.UP)) {
            if (currentOption == 0) {
                currentOption = 1;
            } else {
                currentOption--;
            }
        }
    }

    public void render(Graphics2D graphics) {
        for (int i = 0; i < options.length; i++) {
            if (i == currentOption) {
                graphics.setFont(new Font(null, 0, 70));
                graphics.setColor(Color.WHITE);
            } else {
                graphics.setColor(new Color(255, 255, 125, 50));
                graphics.setFont(new Font(null, 0, 50));
            }
            graphics.drawString(options[i], 50 + i, 150 + (100 * i));
        }
    }
}
