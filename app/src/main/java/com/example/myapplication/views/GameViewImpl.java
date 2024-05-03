package com.example.myapplication.views;

import android.view.View;
import android.widget.TextView;

import com.example.myapplication.presenter.GameStatus;
import com.example.myapplication.presenter.GameView;
import com.example.myapplication.presenter.Point;
import com.example.myapplication.values.GameValues;

class GameViewImpl implements GameView {
    private final GameFrame mGameFrame;
    private final TextView mGameLineText; //mGameScoreText
    private final TextView mGameStatusText;
    private final TextView mGameScoreText;

    GameViewImpl(GameFrame gameFrame, TextView gameLineText, TextView gameStatusText, TextView gameScoreText) {
        mGameFrame = gameFrame;
        mGameLineText = gameLineText;
        mGameStatusText = gameStatusText;
        mGameScoreText = gameScoreText;
    }

    @Override
    public void init(int gameSize) {
        mGameFrame.init(gameSize);
    }

    @Override
    public void draw(Point[][] points) {
        mGameFrame.setPoints(points);
        mGameFrame.invalidate();
    }

    @Override
    public void setLines(int line) {
        mGameLineText.setText("Lines: " + line);
    }

    @Override
    public void setScore(int tmp) {

        if (((tmp - GameValues.lines) * 1000) != 0 || ((tmp - GameValues.lines) * 1000) <= 0) {
            GameValues.tmp = (tmp - GameValues.lines) * 1000;
            GameValues.score += GameValues.tmp;
            mGameScoreText.setText("Score: " + GameValues.score);
            GameValues.lines = tmp;
            GameValues.tmp = tmp;
        }
    }

    @Override
    public void setStatus(GameStatus status) {
        mGameStatusText.setText(status.getValue());
    }
}
