package com.figueroaluis.dormdash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class OrderScreen extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.imageButton_profile) {
            // log for us to check
            Log.i("Open Sign Up", "Was pressed");
            Intent intent = new Intent(OrderScreen.this, LogSign.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.imageButton_homebutton) {
            Log.i("Open Sign Up", "Was pressed");
            Intent intent = new Intent(OrderScreen.this, MainActivity.class);
            startActivity(intent);
        }
    }

    ImageButton homeButton;
    ImageButton searchButton;
    //ImageButton ordersButton;
    ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);

        Button placeButton;

        placeButton = (Button) findViewById(R.id.placebutton);
        placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ordertext = (EditText) findViewById(R.id.orderedittext);

                String orderString = ordertext.getText().toString();

                Toast.makeText(OrderScreen.this, orderString,
                        Toast.LENGTH_SHORT).show();

            }
        });

        profileButton = (ImageButton) findViewById(R.id.imageButton_profile);
        profileButton.setOnClickListener(OrderScreen.this);

        homeButton = (ImageButton) findViewById(R.id.imageButton_homebutton);
        homeButton.setOnClickListener(OrderScreen.this);

        searchButton = (ImageButton) findViewById(R.id.imageButton_search);
        searchButton.setOnClickListener(OrderScreen.this);

    }
}
