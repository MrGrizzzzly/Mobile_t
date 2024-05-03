package com.example.myapplication.presenter;

public interface GameView {
    void init(int gameSize);
    void draw(Point[][] points);
    void setLines(int line);
    void setScore(int tmp);
    void setStatus(GameStatus status);
}
