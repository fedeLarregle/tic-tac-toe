package com.larregle.entities;

import com.larregle.engine.MouseHandler;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Board {

    private final Cell[][] cells;
    private final List<Cell> winningCells;
    private final List<Cell> rootValues;
    private Player.Type currentPlayer;
    private Player.Type winner;

    public Board() {
        this.cells = new Cell[3][3];
        this.winningCells = new ArrayList<>(3);
        this.rootValues = new ArrayList<>();
        this.currentPlayer = Player.Type.USER;
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                cells[i][j] = new Cell(i, j);
    }

    public boolean isRunning() {
        winningCells.clear();
        if (isWinning(Player.Type.USER)) { winner = Player.Type.USER; return false; }
        if (isWinning(Player.Type.COMPUTER)) { winner = Player.Type.COMPUTER; return false; }
        if (getEmptyCells().isEmpty()) { winner = null; return false; }
        return true;
    }

    public boolean isWinning(Player.Type player) {
        if ( cells[0][0].getCellState().toString().equals(player.toString()) &&
                cells[1][1].getCellState().toString().equals(player.toString()) &&
                cells[2][2].getCellState().toString().equals(player.toString()) ) {
            winningCells.add(cells[0][0]);
            winningCells.add(cells[1][1]);
            winningCells.add(cells[2][2]);
            return true;
        }

        if( cells[0][2].getCellState().toString().equals(player.toString()) &&
                cells[1][1].getCellState().toString().equals(player.toString()) &&
                cells[2][0].getCellState().toString().equals(player.toString()) ){
            winningCells.add(cells[0][2]);
            winningCells.add(cells[1][1]);
            winningCells.add(cells[2][0]);
            return true;
        }

        for (int i = 0; i < cells.length; i++) {

            // checking the rows
            if ( cells[i][0].getCellState().toString().equals(player.toString()) &&
                    cells[i][1].getCellState().toString().equals(player.toString()) &&
                    cells[i][2].getCellState().toString().equals(player.toString()) ) {

                winningCells.add(cells[i][0]);
                winningCells.add(cells[i][1]);
                winningCells.add(cells[i][2]);
                return true;
            }

            // checking the columns
            if( cells[0][i].getCellState().toString().equals(player.toString()) &&
                    cells[1][i].getCellState().toString().equals(player.toString()) &&
                    cells[2][i].getCellState().toString().equals(player.toString()) ){
                winningCells.add(cells[0][i]);
                winningCells.add(cells[1][i]);
                winningCells.add(cells[2][i]);
                return true;
            }
        }

        return false;
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

                            if (!cells[i][j].getCellState().equals(Cell.CellState.EMPTY))
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

    public void computerMove() {
        if (!isRunning()) { return; }

        rootValues.clear();
        minimax(0, Player.Type.COMPUTER);
        Cell cell = rootValues.stream().max(Comparator.comparing(Cell::getMinimaxValue)).get();
        cells[cell.getX()][cell.getY()].setCellState(Cell.CellState.COMPUTER);
        currentPlayer = Player.Type.USER;
    }

    public int minimax(int depth, Player.Type player) {

        if (isWinning(Player.Type.COMPUTER)) { return +1; }
        if (isWinning(Player.Type.USER)) { return -1; }

        List<Cell> availableCells = getEmptyCells();

        if (availableCells.isEmpty()) { return 0; }

        List<Integer> scores = new ArrayList<>();

        for (int i = 0; i < availableCells.size(); i++) {
            Cell cell = availableCells.get(i);

            if (player.equals(Player.Type.COMPUTER)) {
                cells[cell.getX()][cell.getY()].setCellState(Cell.CellState.COMPUTER);
                int currentScore = minimax(depth + 1, Player.Type.USER);
                scores.add(currentScore);
                if (depth == 0) {
                    cell.setMinimaxValue(currentScore);
                    rootValues.add(cell);
                }
            } else if (player.equals(Player.Type.USER)){
                cells[cell.getX()][cell.getY()].setCellState(Cell.CellState.USER);
                scores.add(minimax(depth + 1, Player.Type.COMPUTER));
            }

            cells[cell.getX()][cell.getY()].setCellState(Cell.CellState.EMPTY);
        }

        if (player.equals(Player.Type.COMPUTER)) {
            return scores.stream().max(Integer::compareTo).get();
        }

        return scores.stream().min(Integer::compareTo).get();
    }

    public List<Cell> getEmptyCells() {
        List<Cell> result = new ArrayList<>();

        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                if (cells[i][j].getCellState().equals(Cell.CellState.EMPTY))
                    result.add(cells[i][j]);

        return result;
    }

    public Cell[][] getCells() { return this.cells; }

    public Player.Type getWinner() { return winner; }

    public List<Cell> getWinningCells() { return winningCells; }

    public Player.Type getCurrentPlayer() { return currentPlayer; }
}
