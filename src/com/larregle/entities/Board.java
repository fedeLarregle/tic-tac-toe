package com.larregle.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;

public class Board {

    private final Cell[][] cells;

    public Board() {
        this.cells = new Cell[3][3];
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                cells[i][j] = new Cell(i, j);
    }

    public Cell[][] getCells() { return cells; }

    public void update() {}

    public void render(Graphics2D graphics) {
        // Just a little title
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setFont(new Font(null, 0, 50));
        graphics.drawString("TIC TAC TOE",155,50);
        // Drawing all the cells
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                graphics.setColor(Color.WHITE);
                graphics.setStroke(new BasicStroke(3.0F));
                graphics.drawRect((i + (Cell.WIDTH * i)) + 100, (j + (Cell.HEIGHT * j)) + 100, Cell.WIDTH, Cell.HEIGHT);
            }
        }
    }
}
