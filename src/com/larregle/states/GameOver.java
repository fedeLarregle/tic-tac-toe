package com.larregle.states;

import com.larregle.engine.KeyHandler;
import com.larregle.engine.TicTacToeGame;
import com.larregle.entities.Cell;
import com.larregle.entities.Player;

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
            System.exit(0);
        }
    }

    public void render(Graphics2D graphics) {
        // Render last state of board
        game.renderGameRunning(graphics);
        // Render winning move
        for (Cell cell : game.getBoard().getWinningCells()) {
            graphics.setStroke(new BasicStroke(5));
            if (game.getBoard().getWinner().equals(Player.Type.COMPUTER)) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.GREEN);
            }
            graphics.drawRect(cell.getScreenX(), cell.getScreenY(), Cell.WIDTH, Cell.HEIGHT);
        }
        // Render final question
        graphics.setColor(new Color(0f,0f,0f,.8f ));
        graphics.fillRect(0, 0, 650, 650);
        graphics.setFont(new Font(null, 0, 30));
        graphics.setColor(Color.WHITE);
        graphics.drawString("PRESS ENTER FOR MENU.", 50, 150 + 100);
        graphics.drawString("ESCAPE TO QUIT.", 50, 150 + 200);
    }
}
