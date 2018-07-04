package com.larregle.engine;

import com.larregle.entities.Board;
import com.larregle.states.GameOver;
import com.larregle.states.GameRunning;
import com.larregle.states.Menu;

import java.awt.Graphics2D;

public class TicTacToeGame {

    private Board board;
    private GameState gameState;
    private Menu gameMenu;
    private GameOver gameOver;
    private GameRunning gameRunning;

    public TicTacToeGame() {
        board = new Board();
        gameMenu = new Menu(this);
        gameState = GameState.GAME_MENU;
    }

    public void resetState() {
        MouseHandler.getInstance().setClickedPerformed(false);
        board = new Board();
        gameMenu = new Menu(this);
        gameState = GameState.GAME_MENU;
    }

    public void update() {
        switch (gameState) {
            case GAME_MENU: {
                updateMenu();
                break;
            }
            case GAME_RUNNING: {
                gameRunning = new GameRunning(this);
                updateGameRunning();
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

    public void updateGameOver() { gameOver.update(); }

    public void updateGameRunning() { gameRunning.update(); }

    public boolean isRunning() { return board.isRunning(); }

    public void render(Graphics2D graphics) {
        switch (gameState) {
            case GAME_MENU: {
                renderMenu(graphics);
                break;
            }
            case GAME_RUNNING: {
                if (isRunning()) {
                    renderGameRunning(graphics);
                } else {
                    gameState = GameState.GAME_OVER;
                }
                break;
            }
            case GAME_OVER: {
                renderGameOver(graphics);
                break;
            }
        }
    }

    public void renderMenu(Graphics2D graphics) { gameMenu.render(graphics); }

    public void renderGameRunning(Graphics2D graphics) {
        if (gameRunning == null) { gameRunning = new GameRunning(this); }
        gameRunning.render(graphics);
    }

    public void renderGameOver(Graphics2D graphics) { gameOver.render(graphics); }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Board getBoard() { return this.board; }

    public static enum GameState {
        GAME_MENU, GAME_RUNNING, GAME_OVER
    }
}
