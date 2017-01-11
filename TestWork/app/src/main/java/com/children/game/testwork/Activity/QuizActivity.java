package com.children.game.testwork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.children.game.testwork.Fragment.QuizFragment;
import com.children.game.testwork.JavaClass.BaseClass;
import com.children.game.testwork.R;

public class QuizActivity extends AppCompatActivity {

    private QuizFragment quizFragment = new QuizFragment();
    private BaseClass baseClass = new BaseClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    @Override                                  //Обработчик кнопки "Назад"
    public void onBackPressed() {
        baseClass.stopSound(quizFragment.getMediaPlayer());
        super.onBackPressed();
    }
}
