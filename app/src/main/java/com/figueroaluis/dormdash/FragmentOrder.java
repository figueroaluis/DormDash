package com.figueroaluis.dormdash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentOrder extends Fragment implements View.OnClickListener  {


    Button placeButton;


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_placeButton){

            Toast.makeText(view.getContext(), "Placed Order", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_order, container, false);


        placeButton = (Button) view.findViewById(R.id.button_placeButton);
        placeButton.setOnClickListener(this);


        return view;

    }
}
