package com.larregle.engine;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseHandler extends MouseAdapter {

    public static final MouseHandler instance;
    private Point pointClicked;
    private boolean clickedPerformed;

    static {
        instance = new MouseHandler();
    }

    private MouseHandler() { clickedPerformed = false; }

    public static MouseHandler getInstance() { return instance; }

    public Point getPointClicked() {
        return pointClicked;
    }

    public boolean isClickedPerformed() {
        return clickedPerformed;
    }

    public void setClickedPerformed(boolean clickedPerformed) {
        this.clickedPerformed = clickedPerformed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        pointClicked = new Point(e.getX(), e.getY());
        clickedPerformed = true;
    }
}
