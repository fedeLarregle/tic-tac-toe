package com.larregle.states;

import com.larregle.engine.KeyHandler;
import com.larregle.engine.TicTacToeGame;

import java.awt.*;

public class GameOver {

    private TicTacToeGame game;

    public GameOver(TicTacToeGame game) {
        this.game = game;
    }

    public void update() {
        if (KeyHandler.getInstance().wasKeyTyped(KeyHandler.ENTER)) {
            game.resetState();
        } else if (KeyHandler.getInstance().wasKeyTyped(KeyHandler.ESCAPE)) {
            game.quit(true);
        }
    }

    public void render(Graphics2D graphics) {
        graphics.setFont(new Font(null, 0, 30));
        graphics.setColor(new Color(255, 255, 225, 50));
        graphics.drawString("PRESS ENTER FOR MENU.", 50, 150 + 100);
        graphics.drawString("ESCAPE TO QUIT.", 50, 150 + 200);
    }
}
