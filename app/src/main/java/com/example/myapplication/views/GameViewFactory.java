package com.example.myapplication.views;

import android.widget.TextView;

import com.example.myapplication.presenter.GameView;

public class GameViewFactory {


    public static GameView newGameView(GameFrame gameFrame, TextView gameLineText, TextView gameStatusText, TextView gameScoreText) {
        return new GameViewImpl(gameFrame, gameLineText, gameStatusText, gameScoreText);
    }
}
