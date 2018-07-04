package com.larregle.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class KeyHandler extends KeyAdapter {

    private static final KeyHandler instance;

    static {
        instance = new KeyHandler();
    }

    public static final int NUMBER_KEYS = 6;

    public final boolean keyStates[];

    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    public static final int ENTER = 4;
    public static final int ESCAPE = 5;

    private KeyHandler() {
        // Setting default key state values
        keyStates = new boolean[NUMBER_KEYS];
        Arrays.fill(keyStates, Boolean.FALSE);
    }

    public static KeyHandler getInstance() { return instance; }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            toggleKeys(UP);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            toggleKeys(LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            toggleKeys(DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            toggleKeys(RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            toggleKeys(ENTER);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            toggleKeys(ESCAPE);
        }
    }

    public boolean wasKeyTyped(int key) {
        boolean result = keyStates[key];
        if (result) { toggleKeys(); }
        return result;
    }

    public void toggleKeys() {
        for (int i = 0; i < NUMBER_KEYS; i++)
            keyStates[i] = false;
    }

    public void toggleKeys(int key) {
        for (int i = 0; i < NUMBER_KEYS; i++) {
            if (i == key) {
                keyStates[i] = true;
            } else {
                keyStates[i] = false;
            }
        }
    }
}
