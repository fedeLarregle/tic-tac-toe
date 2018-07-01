package com.larregle.entities;

import com.larregle.engine.MouseHandler;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Board {

    private final Cell[][] cells;
    private final Cell[] winningCells;
    private Player.Type currentPlayer;

    public Board() {
        this.cells = new Cell[3][3];
        this.winningCells = new Cell[3];
        this.currentPlayer = Player.Type.USER;
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                cells[i][j] = new Cell(i, j);
    }

    public boolean isRunning() {
        if (isWinning(Player.Type.USER)) { return false; }
        if (isWinning(Player.Type.COMPUTER)) { return false; }

        return true;
    }

    public void update() {
        playerMove();
        if (currentPlayer.equals(Player.Type.COMPUTER)) {
            computerMove();
        }
    }

    public boolean isWinning(Player.Type player) {
        if ( cells[0][0].getCellState().toString().equals(player.toString()) &&
                cells[1][1].getCellState().toString().equals(player.toString()) &&
                cells[2][2].getCellState().toString().equals(player.toString()) ) {
            winningCells[0] = cells[0][0];
            winningCells[1] = cells[1][1];
            winningCells[2] = cells[2][2];
            return true;
        }

        if( cells[0][2].getCellState().toString().equals(player.toString()) &&
                cells[1][1].getCellState().toString().equals(player.toString()) &&
                cells[2][0].getCellState().toString().equals(player.toString()) ){
            winningCells[0] = cells[0][2];
            winningCells[1] = cells[1][1];
            winningCells[2] = cells[2][0];
            return true;
        }

        for (int i = 0; i < cells.length; i++) {

            // checking the rows
            if ( cells[i][0].getCellState().toString().equals(player.toString()) &&
                    cells[i][1].getCellState().toString().equals(player.toString()) &&
                    cells[i][2].getCellState().toString().equals(player.toString()) ) {
                winningCells[0] = cells[i][0];
                winningCells[1] = cells[i][1];
                winningCells[2] = cells[i][2];
                return true;
            }

            // checking the columns
            if( cells[0][i].getCellState().toString().equals(player.toString()) &&
                    cells[1][i].getCellState().toString().equals(player.toString()) &&
                    cells[2][i].getCellState().toString().equals(player.toString()) ){
                winningCells[0] = cells[0][i];
                winningCells[1] = cells[1][i];
                winningCells[2] = cells[2][i];
                return true;
            }
        }

        return false;
    }

    public void computerMove() {
        if (!isRunning()) { return; }
        List<Cell> emptyCells = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                if (cells[i][j].getCellState().equals(Cell.CellState.EMPTY))
                    emptyCells.add(cells[i][j]);

        if (!emptyCells.isEmpty()) {
            int computerChose = random.nextInt(emptyCells.size() - 1) + 1;
            cells[emptyCells.get(computerChose).getX()][emptyCells.get(computerChose).getY()].setCellState(Cell.CellState.COMPUTER);
            currentPlayer = Player.Type.USER;
        }
    }

    public void playerMove() {
        if (!isRunning()) { return; }
        // Dummy logic to update the cell state
        if (MouseHandler.getInstance().isClickedPerformed()) {
            outer:
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    Point pointClicked = MouseHandler.getInstance().getPointClicked();
                    if (pointClicked != null) {
                        if (new Rectangle(cells[i][j].getScreenX(), cells[i][j].getScreenY(), Cell.WIDTH, Cell.HEIGHT)
                                .contains(pointClicked)) {

                            if (cells[i][j].getCellState().equals(Cell.CellState.USER))
                                break outer;

                            cells[i][j].setCellState(Cell.CellState.USER);
                            currentPlayer = Player.Type.COMPUTER;
                            MouseHandler.getInstance().setClickedPerformed(false);
                            break outer;
                        }
                    }
                }
            }
        }
    }

    public void render(Graphics2D graphics) {
        // Just a little title
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setFont(new Font(null, 0, 50));
        graphics.drawString("TIC TAC TOE",155,50);
        // Drawing all the cells
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int x = (i + (Cell.WIDTH * i)) + 100;
                int y = (j + (Cell.HEIGHT * j)) + 100;
                graphics.setColor(Color.WHITE);
                graphics.setStroke(new BasicStroke(3.0F));
                graphics.drawRect((i + (Cell.WIDTH * i)) + 100, (j + (Cell.HEIGHT * j)) + 100, Cell.WIDTH, Cell.HEIGHT);
                drawCellState(graphics, x, y, cells[i][j].getCellState());
                if (cells[i][j].getScreenX() < 0) {
                    cells[i][j].setScreenX(x);
                    cells[i][j].setScreenY(y);
                }
            }
        }
    }

    public void drawCellState(Graphics2D graphics, int x, int y, Cell.CellState state) {
        graphics.setFont(new Font(null, 0, 100));
        if (state.equals(Cell.CellState.EMPTY)) {
            graphics.drawString("", x + Cell.WIDTH / 4, y + (Cell.WIDTH / 2 + Cell.WIDTH / 4));
        } else if (state.equals(Cell.CellState.USER)) {
            graphics.drawString("X", x + Cell.WIDTH / 4, y + (Cell.WIDTH / 2 + Cell.WIDTH / 4));
        } else if (state.equals(Cell.CellState.COMPUTER)) {
            graphics.drawString("O", x + Cell.WIDTH / 4, y + (Cell.WIDTH / 2 + Cell.WIDTH / 4));
        }
    }

    public Player.Type getCurrentPlayer() {
        return currentPlayer;
    }

    public Cell[] getWinningCells() { return winningCells; }
}
