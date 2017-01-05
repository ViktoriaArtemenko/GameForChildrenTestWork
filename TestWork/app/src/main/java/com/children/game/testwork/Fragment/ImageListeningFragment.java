package com.children.game.testwork.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.children.game.testwork.JavaClass.BaseClass;
import com.children.game.testwork.R;

public class ImageListeningFragment extends BaseClass {

    protected String transportOrAnimal;
    protected String[] transportArray;
    private int columnCount = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, null);
        transportArray = getResources().getStringArray(R.array.transport_array);
        animalArray = getResources().getStringArray(R.array.animal_array);
        gridLayout = (GridLayout) view.findViewById(R.id.grid_layout);
        gridLayout.setColumnCount(columnCount);
        generalOfIconButton(choiceArray());
        return view;
    }

    public void setStringTransportOrAnimal(String transportOrAnimal) {
        this.transportOrAnimal = transportOrAnimal;
    }

    public String[] choiceArray() {   //Выбираем массив "транспорт" или "звери" - в завиимости
        String[] array = null;            //от того, на какую кнопку нажал пользователь
        try {
            if (transportOrAnimal.equals(getString(R.string.animal_send))) array = animalArray;
            else array = transportArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    @Override
    public void onClick(View v) {
        try {
            if (mediaPlayer != null) mediaPlayer.stop();
        } catch (Exception e) {
        }
        mediaPlayer = MediaPlayer.create(getActivity(), getIdResources((String) v.getTag(), "raw"));
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    public String getTransportOrAnimal() {
        return transportOrAnimal;
    }
}


