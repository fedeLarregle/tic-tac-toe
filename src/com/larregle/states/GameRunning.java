package com.larregle.states;

import com.larregle.engine.TicTacToeGame;
import com.larregle.entities.Cell;
import com.larregle.entities.Player;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class GameRunning {

    private final TicTacToeGame game;

    public GameRunning(TicTacToeGame game) { this.game = game; }

    public void update() {
        game.getBoard().playerMove();
        if (game.getBoard().getCurrentPlayer().equals(Player.Type.COMPUTER)) {
            game.getBoard().computerMove();
        }
    }

    public void render(Graphics2D graphics) {
        // Just a little title
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setFont(new Font(null, 0, 50));
        graphics.drawString("TIC TAC TOE",155,50);

        Cell[][] cells = game.getBoard().getCells();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                graphics.setColor(Color.WHITE);
                graphics.setStroke(new BasicStroke(3.0F));
                drawLines(graphics);
                drawCellState(graphics, cells[i][j].getScreenX(), cells[i][j].getScreenY(), cells[i][j].getCellState());
            }
        }

    }

    private void drawLines(Graphics2D graphics) {
        int x = 101;
        int y = 101;

        int x1 = (1 + (Cell.WIDTH)) + 100;
        int y1 = (1 + (Cell.HEIGHT)) + 100;

        int x2 = (2 + (Cell.WIDTH * 2)) + 100;
        int y2 = (2 + (Cell.HEIGHT * 2)) + 100;

        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(3.0F));
        graphics.drawLine(x, y1, (Cell.WIDTH * 3) + x, y1);
        graphics.drawLine(x, y2, (Cell.WIDTH * 3) + x, y2);
        graphics.drawLine(x1, y, x1, (y + Cell.HEIGHT * 3));
        graphics.drawLine(x2, y, x2, (y + Cell.HEIGHT * 3));

    }

    private void drawCellState(Graphics2D graphics, int x, int y, Cell.CellState state) {
        graphics.setFont(new Font(null, 0, 100));
        if (state.equals(Cell.CellState.EMPTY)) {
            graphics.drawString("", x + Cell.WIDTH / 4, y + (Cell.WIDTH / 2 + Cell.WIDTH / 4));
        } else if (state.equals(Cell.CellState.USER)) {
            graphics.drawString("X", x + Cell.WIDTH / 4, y + (Cell.WIDTH / 2 + Cell.WIDTH / 4));
        } else if (state.equals(Cell.CellState.COMPUTER)) {
            graphics.drawString("O", x + Cell.WIDTH / 4, y + (Cell.WIDTH / 2 + Cell.WIDTH / 4));
        }
    }
}
