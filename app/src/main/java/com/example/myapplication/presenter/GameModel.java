package com.example.myapplication.presenter;

public interface GameModel {
    int FPS = 60;
    int SPEED = 45;
    void init();
    int getGameSize();
    void newGame();
    void startGame(PresenterObserver<Point[][]> onGameDrawnListener);
    void pauseGame();
    void turn(GameTurn turn);
    void setGameOverListener(PresenterCompletableObserver onGameOverListener);
    void setLineUpdatedListener(PresenterObserver<Integer> onLineUpdatedListener);
    void setScoreUpdatedListener(PresenterObserver<Integer> onScoreUpdatedListener);
}
