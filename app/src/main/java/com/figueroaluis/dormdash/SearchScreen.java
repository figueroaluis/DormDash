package com.figueroaluis.dormdash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class SearchScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.imageButton_profile) {
            // log for us to check
            Log.i("Open Sign Up", "Was pressed");
            Intent intent = new Intent(SearchScreen.this, FragmentLogSign.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.imageButton_homebutton) {
            Log.i("Open Sign Up", "Was pressed");
            Intent intent = new Intent(SearchScreen.this, MainActivity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.imageButton_orders) {
            Log.i("Open Sign Up", "Was pressed");
            Intent intent = new Intent(SearchScreen.this, OrderScreen.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        ImageButton homeButton;
        ImageButton searchButton;
        //ImageButton orderButton;
        ImageButton profileButton;

        profileButton = (ImageButton) findViewById(R.id.imageButton_profile);
        profileButton.setOnClickListener(SearchScreen.this);

        homeButton = (ImageButton) findViewById(R.id.imageButton_homebutton);
        homeButton.setOnClickListener(SearchScreen.this);

        searchButton = (ImageButton) findViewById(R.id.imageButton_search);
        searchButton.setOnClickListener(SearchScreen.this);

    }
}
