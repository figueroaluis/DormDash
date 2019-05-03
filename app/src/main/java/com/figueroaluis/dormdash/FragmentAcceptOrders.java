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

public class FragmentAcceptOrders extends Fragment implements View.OnClickListener  {


    Button acceptButton;


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_accept){

            Toast.makeText(view.getContext(), "Order Accepted", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_accept_orders, container, false);


        acceptButton = (Button) view.findViewById(R.id.button_accept);
        acceptButton.setOnClickListener(this);


        return view;

    }
}