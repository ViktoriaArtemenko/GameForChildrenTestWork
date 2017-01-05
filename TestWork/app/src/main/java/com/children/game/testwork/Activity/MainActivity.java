package com.children.game.testwork.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.children.game.testwork.R;
import com.children.game.testwork.Singleton.SingletonStartHomeActivity;

public class MainActivity extends AppCompatActivity {
    private SingletonStartHomeActivity startHomeActivity;
    private Handler handler = new Handler();
    private int time = 5 * 1000;   //Время, через которое появится главное меню - 5 секунд

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (startHomeActivity == null)
            handler.postDelayed(getMainThread(), time);
        startHomeActivity = SingletonStartHomeActivity.getInstance();
    }

    public Runnable getMainThread() {
        Runnable thread = new Runnable() {  //Создаем новый поток, запускаем HomeActivity
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };
        return thread;
    }
}
