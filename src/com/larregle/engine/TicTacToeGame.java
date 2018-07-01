package com.larregle.engine;

import com.larregle.entities.Board;
import com.larregle.entities.Cell;
import com.larregle.entities.Player;

import java.awt.*;

public class TicTacToeGame {

    public final Board board;

    public TicTacToeGame() {
        board = new Board();
    }

    public void updateBoard() { board.update(); }

    public boolean isRunning() { return board.isRunning(); }

    public void renderBoard(Graphics2D graphics) {
        board.render(graphics);
    }

    public void renderEndState(Graphics2D graphics) {
        graphics.setColor(new Color(0f,0f,0f,.5f ));
        graphics.fillRect(0, 0, 650, 650);
        for (Cell cell : board.getWinningCells()) {
            graphics.setStroke(new BasicStroke(5));
            if (board.getWinner().equals(Player.Type.COMPUTER)) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.GREEN);
            }
            graphics.drawRect(cell.getScreenX(), cell.getScreenY(), Cell.WIDTH, Cell.HEIGHT);
        }
    }
}
