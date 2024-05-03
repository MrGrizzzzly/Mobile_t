package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.db.DataBase;
import com.example.myapplication.models.GameModelFactory;
import com.example.myapplication.models.GameType;
import com.example.myapplication.presenter.GamePresenter;
import com.example.myapplication.presenter.GameTurn;
import com.example.myapplication.values.GameValues;
import com.example.myapplication.views.GameFrame;
import com.example.myapplication.views.GameViewFactory;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {


    DataBase dataBase = new DataBase(this);
    String user_id, user_name, user_score, user_country;
    GamePresenter gamePresenter = new GamePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameFrame gameFrame = findViewById(R.id.game_container);
        TextView gameLineText = findViewById(R.id.game_line);
        TextView gameStatusText = findViewById(R.id.game_status);
        TextView gameScoreText = findViewById(R.id.game_score);

        gamePresenter.setGameModel(GameModelFactory.newGameModel(GameType.TETRIS));
        gamePresenter.setGameView(GameViewFactory.newGameView(gameFrame, gameLineText, gameStatusText, gameScoreText));

        UserData();
        Button downBtn = findViewById(R.id.down_btn);
        Button downBtn2 = findViewById(R.id.down_btn2);
        Button leftBtn = findViewById(R.id.left_btn);
        Button rightBtn = findViewById(R.id.right_btn);
        Button fireBtn = findViewById(R.id.fire_btn);
        Button fireBtn2 = findViewById(R.id.fire_btn2);

        downBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.DOWN));
        downBtn2.setOnClickListener(v -> gamePresenter.turn(GameTurn.DOWN));
        leftBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.LEFT));
        rightBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.RIGHT));
        fireBtn.setOnClickListener(v -> gamePresenter.turn(GameTurn.ROTATE_RIGHT));
        fireBtn2.setOnClickListener(v -> gamePresenter.turn(GameTurn.ROTATE_LEFT));

        gamePresenter.init();
        gamePresenter.changeStatus();

        new Timer().scheduleAtFixedRate(
                new TimerTask(){
                    public void run(){
                        if (Objects.equals(GameValues.status, "OVER"))
                            if(GameValues.score >= Integer.parseInt(user_score))
                                updateDataBaseSQLite();
                    }
                },
                0,
                1000);

    }

    public void Pause(View view) {
        gamePresenter.changeStatus();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Пауза");
        builder.setCancelable(false);
        builder.setPositiveButton("Продолжить", (dialog, which) -> gamePresenter.changeStatus());
        builder.setNegativeButton("Выйти", (dialog, which) ->
            startActivity(new Intent(this, MainActivity.class)));
        builder.create().show();
    }

    public void updateDataBaseSQLite() {
        dataBase.updateData(user_id, user_name, GameValues.score, user_country);
    }

    public void UserData(){
        Cursor cursor = dataBase.readAllData();
        cursor.moveToNext();
        user_id = cursor.getString(0);
        user_name = cursor.getString(1);
        user_score = cursor.getString(2);
        user_country = cursor.getString(3);

    }

}