package com.children.game.testwork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.children.game.testwork.Fragment.QuizFragment;
import com.children.game.testwork.R;

public class QuizActivity extends AppCompatActivity {

    QuizFragment quizFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizFragment = new QuizFragment();
    }

    @Override                                  //Обработчик кнопки "Назад"
    public void onBackPressed() {
        super.onBackPressed();
        try {
            if (quizFragment.getMediaPlayer() != null) quizFragment.
                    getMediaPlayer().stop();
        } catch (Exception e) {
        }
    }
}
