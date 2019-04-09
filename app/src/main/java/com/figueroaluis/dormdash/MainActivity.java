package com.figueroaluis.dormdash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // variables
    Button openSignUpPageButton;


    // tester onClick to open up the sign in page
    @Override
    public void onClick(View view){
        if(view.getId() == R.id.openSignUpView_button){
            // log for us to check
            Log.i("Open Sign Up", "Was pressed");
            Intent intent = new Intent(MainActivity.this, LogSign.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set the onclick listener
        openSignUpPageButton = findViewById(R.id.openSignUpView_button);
        openSignUpPageButton.setOnClickListener(this);


    }
}
