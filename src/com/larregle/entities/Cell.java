package com.larregle.entities;

public class Cell {

    public static final int WIDTH = 150;
    public static final int HEIGHT = 150;

    private int x;
    private int y;
    private int screenX;
    private int screenY;
    private int minimaxValue;
    private CellState cellState;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.screenX = -1;
        this.screenY = -1;
        this.cellState = CellState.EMPTY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getMinimaxValue() {
        return minimaxValue;
    }

    public void setMinimaxValue(int minimaxValue) {
        this.minimaxValue = minimaxValue;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public enum CellState {
        COMPUTER, USER, EMPTY
    }
}
