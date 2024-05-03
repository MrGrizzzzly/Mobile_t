package com.example.myapplication.presenter;

public enum GameStatus {
    START(""),
    PAUSED(""),
    PLAYING(""),
    OVER("GAME OVER");

    private final String value;

    GameStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
