package com.children.game.testwork.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.children.game.testwork.JavaClass.BaseClass;
import com.children.game.testwork.R;

import java.util.HashSet;
import java.util.Random;

public class QuizFragment extends BaseClass {

    private AlertDialog alertDialog;
    private Random random = new Random();
    private int buttonCountMin = 2;
    private int buttonCountMiddle = 4;
    private int buttonCountMax = 6;
    private int step = 3;
    private int buttonCount;
    private String[] arrayReady;
    private String nameMusicResources;
    private int count = 0;
    private boolean flag = false;

    private String message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        animalArray = getResources().getStringArray(R.array.animal_array);
        gridLayout = (GridLayout) view.findViewById(R.id.grid_layout);
        if (arrayReady == null) initArray(buttonCountMin);
        generalOfIconButton(arrayReady);
        return view;
    }

    public void playSound(String nameResources) {   //Проигрываем музыку
        stopSound();
        mediaPlayer = MediaPlayer.create(getActivity(), getIdResources(nameResources, "raw"));
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    public void initArray(int buttonCount) {
        if (buttonCount > buttonCountMiddle)
            gridLayout.setColumnCount(buttonCountMiddle);
        else gridLayout.setColumnCount(buttonCount);
        HashSet<String> hashSet = new HashSet();
        arrayReady = new String[buttonCount];
        while (hashSet.size() < buttonCount) {
            hashSet.add(animalArray[random.nextInt(animalArray.length)]);
            if (hashSet.size() == buttonCount) ;
        }
        arrayReady = hashSet.toArray(arrayReady);
        int r = random.nextInt(arrayReady.length);
        nameMusicResources = arrayReady[r];
        playSound(nameMusicResources);
    }

    public void showDialog(String message) {//Показываем диалог с сообщением о том, что изображение
        flag = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //выбрана неправильно
        builder.setTitle("Увага!")                                            //или успех
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                gridLayout.removeAllViews();
                                if (count == step && buttonCount == buttonCountMax) {
                                    getActivity().onBackPressed();
                                    return;
                                }
                                initArray(buttonCount);
                                generalOfIconButton(arrayReady);
                                flag = false;
                            }
                        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void stopSound() {
        try {
            if (mediaPlayer != null) mediaPlayer.stop();
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        stopSound();
        if (buttonCount == 0) buttonCount = buttonCountMin;
        if (v.getTag() == nameMusicResources) {
            message = "Вірно";
            showDialog(message);
            count++;
            if (count == step) {
                if (buttonCount == buttonCountMax) {
                    message = "Все вірно!";
                    showDialog(message);
                    return;
                }
                count = 0;
                buttonCount++;
            }
            return;
        } else {
            message = "Це неправильна відповідь! Правильна відповідь: " + nameMusicResources;
            showDialog(message);
            buttonCount = buttonCountMin;
            count = 0;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (flag == true) showDialog(message);
    }
}
