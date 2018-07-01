package com.larregle.engine;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseHandler extends MouseAdapter {

    public static final MouseHandler instance;
    private Point pointClicked;

    static {
        instance = new MouseHandler();
    }

    private MouseHandler() {}

    public static MouseHandler getInstance() { return instance; }

    public Point getPointClicked() { return pointClicked; }

    @Override
    public void mouseClicked(MouseEvent e) {
        pointClicked = new Point(e.getX(), e.getY());
    }
}
