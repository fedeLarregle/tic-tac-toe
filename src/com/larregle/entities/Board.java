package com.larregle.entities;

import com.larregle.engine.MouseHandler;

import java.awt.*;

public class Board {

    private final Cell[][] cells;

    public Board() {
        this.cells = new Cell[3][3];
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                cells[i][j] = new Cell(i, j);
    }

    public Cell[][] getCells() { return cells; }

    public void update() {
        // Dummy logic to update the cell state
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Point pointClicked = MouseHandler.getInstance().getPointClicked();
                if (pointClicked != null) {
                    if (new Rectangle(cells[i][j].getScreenX(), cells[i][j].getScreenY(), Cell.WIDTH, Cell.HEIGHT)
                            .contains(pointClicked)) {
                        cells[i][j].setCellState(Cell.CellState.USER);
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
}
