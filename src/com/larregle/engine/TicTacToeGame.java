package com.larregle.engine;

import com.larregle.entities.Board;
import com.larregle.entities.Cell;
import com.larregle.entities.Player;
import com.larregle.states.GameOver;
import com.larregle.states.Menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class TicTacToeGame {

    private Board board;
    private GameState gameState;
    private Menu gameMenu;
    private GameOver gameOver;
    private boolean quit;

    public TicTacToeGame() {
        board = new Board();
        quit = false;
        gameMenu = new Menu(this);
        gameState = GameState.GAME_MENU;
    }

    public void resetState() {
        board = new Board();
        quit = false;
        gameMenu = new Menu(this);
        gameState = GameState.GAME_MENU;
    }

    public void update() {
        System.out.println("GAME STATE: " + gameState.toString());
        switch (gameState) {
            case GAME_MENU: {
                updateMenu();
                break;
            }
            case GAME_RUNNING: {
                updateBoard();
                break;
            }
            case GAME_PAUSED: {
                updatePaused();
                break;
            }
            case GAME_OVER: {
                gameOver = new GameOver(this);
                updateGameOver();
                break;
            }
        }
    }

    public void updateMenu() { gameMenu.update(); }

    public void updatePaused() {}

    public void updateGameOver() { gameOver.update(); }

    public void updateBoard() { board.update(); }

    public boolean isRunning() { return board.isRunning(); }

    public void render(Graphics2D graphics) {
        switch (gameState) {
            case GAME_MENU: {
                renderMenu(graphics);
                break;
            }
            case GAME_RUNNING: {
                if (isRunning()) {
                    renderBoard(graphics);
                } else {
                    gameState = GameState.GAME_OVER;
                }
                break;
            }
            case GAME_PAUSED: {
                updatePaused();
                break;
            }
            case GAME_OVER: {
                renderGameOver(graphics);
                break;
            }
        }
    }

    public void renderMenu(Graphics2D graphics) { gameMenu.render(graphics); }

    public void renderBoard(Graphics2D graphics) {
        board.render(graphics);
    }

    public void renderGameOver(Graphics2D graphics) { gameOver.render(graphics); }

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

    public boolean hasQuit() {
        return quit;
    }

    public void quit(boolean quit) { this.quit = quit;}

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setBoard(Board board) { this.board = board; }

    public static enum GameState {
        GAME_MENU, GAME_RUNNING, GAME_PAUSED, GAME_OVER
    }
}
