package com.larregle.engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    public static final MouseHandler instance;

    static {
        instance = new MouseHandler();
    }

    private MouseHandler() {}

    public static MouseHandler getInstance() { return instance; }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
