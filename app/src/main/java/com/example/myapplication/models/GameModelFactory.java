package com.example.myapplication.models;

import com.example.myapplication.presenter.GameModel;

public class GameModelFactory {
    private GameModelFactory() {
    }

    public static GameModel newGameModel(GameType gameType) {
        if (gameType == GameType.TETRIS)
            return new TetrisGameModel();
        return null;
    }
}
