package com.larregle.engine;

import com.larregle.entities.Board;

import java.awt.*;

public class TicTacToeGame {

    public final Board board;

    public TicTacToeGame() {
        board = new Board();
    }

    public void updateBoard() {
        board.update();
    }

    public void renderBoard(Graphics2D graphics) {
        board.render(graphics);
    }
}
