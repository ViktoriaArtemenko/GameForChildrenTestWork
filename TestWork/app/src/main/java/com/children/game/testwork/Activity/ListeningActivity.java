package com.children.game.testwork.Activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;

import com.children.game.testwork.Fragment.ImageListeningFragment;
import com.children.game.testwork.Fragment.MenuListeningFragment;
import com.children.game.testwork.R;

public class ListeningActivity extends AppCompatActivity implements MenuListeningFragment.
        OnFragmentInteractionListener {
    private ImageListeningFragment imageListeningFragment;  //Объявляем переменные
    private MenuListeningFragment menuListeningFragment;
    private FragmentTransaction fragmentTransaction;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        menuListeningFragment = new MenuListeningFragment();
        imageListeningFragment = new ImageListeningFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, menuListeningFragment);
        fragmentTransaction.commit();
    }

    @Override                     //Метод, с помощью которого фрагменты обмениваются данными между
    public void onFragmentMenuInteraction(String str) {   //собой и активити
        fragmentTransaction();
        if (imageListeningFragment != null) {
            imageListeningFragment.setStringTransportOrAnimal(str);
        }
    }

    @Override                                  //Обработчик кнопки "Назад"
    public void onBackPressed() {
        gridLayout = (GridLayout) findViewById(R.id.grid_layout);
        if (gridLayout == null) {
            super.onBackPressed();
            return;
        }
        if (menuListeningFragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, menuListeningFragment);
            fragmentTransaction.commit();
            try {
                if (imageListeningFragment.getMediaPlayer() != null) imageListeningFragment.
                        getMediaPlayer().stop();
            } catch (Exception e) {
            }
        }
    }

    public void fragmentTransaction() {     //Меняем фрагменты местами
        if (imageListeningFragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, imageListeningFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {  //Сохраняем данные при повороте экрана
        super.onSaveInstanceState(outState);
        gridLayout = (GridLayout) findViewById(R.id.grid_layout);
        if (gridLayout == null) outState.putBoolean("isFragmentImage", false);
        if (gridLayout != null) {
            outState.putBoolean("isFragmentImage", true);
            outState.putString("object", imageListeningFragment.getTransportOrAnimal());
        }
    }

    @Override                                       //Восстанавливаем данные после поворота экрана
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getBoolean("isFragmentImage"))
            onFragmentMenuInteraction(savedInstanceState.getString("object"));
    }
}
