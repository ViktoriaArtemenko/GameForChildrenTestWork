package com.children.game.testwork.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.children.game.testwork.R;

public class MenuListeningFragment extends Fragment implements View.OnClickListener {

    private Button buttonAnimal;
    private Button buttonTransport;
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_listening, container, false);
        buttonAnimal = (Button) view.findViewById(R.id.button_animal);
        buttonTransport = (Button) view.findViewById(R.id.button_transport);
        buttonAnimal.setOnClickListener(this);
        buttonTransport.setOnClickListener(this);
        return view;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentMenuInteraction(String id);
    }

    @Override             //Взаимодействие с Activity
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override                         //Обработчик нажатия на кнопки
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_animal:
                mListener.onFragmentMenuInteraction(getString(R.string.animal_send));
                break;
            case R.id.button_transport:
                mListener.onFragmentMenuInteraction(getString(R.string.transport_send));
                break;
            default:
                break;
        }
    }
}
