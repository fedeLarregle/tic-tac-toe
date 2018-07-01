package com.larregle.entities;

public class Player {

    private final Type type;

    public Player(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public static enum Type {
        COMPUTER, USER
    }
}
