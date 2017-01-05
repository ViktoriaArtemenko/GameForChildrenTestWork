package com.children.game.testwork.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.children.game.testwork.R;

public class HomeActivity extends AppCompatActivity {

      private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick(View v) {  //Обработчик события нажатия на кнопки
        switch (v.getId()) {
            case R.id.button_listening:
                intent = new Intent(HomeActivity.this, ListeningActivity.class);
                startActivity(intent);
                break;
            case R.id.button_quiz:
                intent = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
