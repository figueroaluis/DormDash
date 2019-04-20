package com.figueroaluis.dormdash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentHome extends Fragment implements View.OnClickListener {

    Button marketplaceButton;
    Button coolerButton;
    Button greenbeanButton;
    Button coffeecartButton;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.marketplace_button){
            Toast.makeText(view.getContext(), "Marketplace button was pressed.", Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.cooler_button){
            Toast.makeText(view.getContext(), "Cooler button was pressed.", Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.greenbean_button){
            Toast.makeText(view.getContext(), "Green bean button was pressed.", Toast.LENGTH_SHORT).show();
        } else if(view.getId() == R.id.coffeecart_button){
            Toast.makeText(view.getContext(), "Coffee cart button was pressed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        marketplaceButton = view.findViewById(R.id.marketplace_button);
        marketplaceButton.setOnClickListener(this);
        coolerButton = view.findViewById(R.id.cooler_button);
        coolerButton.setOnClickListener(this);
        greenbeanButton = view.findViewById(R.id.greenbean_button);
        greenbeanButton.setOnClickListener(this);
        coffeecartButton = view.findViewById(R.id.coffeecart_button);
        coffeecartButton.setOnClickListener(this);

        return view;
    }
}
