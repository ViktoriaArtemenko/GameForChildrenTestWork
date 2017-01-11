package com.children.game.testwork.JavaClass;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Random;

public class BaseClass extends Fragment implements View.OnClickListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    protected String[] animalArray;
    protected MediaPlayer mediaPlayer;
    protected GridLayout gridLayout;
    protected Button button;

    protected void generalOfIconButton(String[] array) {  //Создаем кнопки с изображением зверей или
        for (int i = 0; i < array.length; i++) {            //транспорта
            button = new Button(getContext());
            button.setBackgroundResource(getIdResources(array[i], "mipmap"));
            button.setTag(array[i]);
            button.setOnClickListener(this);
            gridLayout.addView(button);
        }
    }

    protected int getIdResources(String nameOfResources, String defType) {  //Узнаем id необходимого
        int id, randomCheckout;          //ресурса с помощью имени ресурса
        String name;
        name = nameOfResources + "_1";
        id = getResources().getIdentifier(name, defType, getContext().getPackageName());
        if (id != 0) {
            Random random = new Random();
            randomCheckout = random.nextInt(10);
            if (randomCheckout <= 4) nameOfResources = name;
            else nameOfResources = nameOfResources + "_2";
        }
        id = getResources().getIdentifier(nameOfResources, defType, getContext().getPackageName());
        return id;
    }

    protected void playSound(String nameResources) {   //Проигрываем музыку
        stopSound(mediaPlayer);
        mediaPlayer = MediaPlayer.create(getActivity(), getIdResources(nameResources, "raw"));
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    public void stopSound(MediaPlayer mediaPlayer) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void onClick(View v) {
    }
}
