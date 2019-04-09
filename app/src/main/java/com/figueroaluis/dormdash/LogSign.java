package com.figueroaluis.dormdash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogSign extends AppCompatActivity implements View.OnClickListener{

    // check to see if we are in sign up or log in
    Boolean signUpMode = true;
    // buttons and textviews
    TextView loginTextView;


    // implement a function that allows the button to open another view
    @Override
    public void onClick(View view){
        // check that it's the proper view
        if(view.getId() == R.id.logIn_textView){
            // log for us to keep track of it
            Log.i("Switch", "Was tapped");

            Button signUpButton = findViewById(R.id.signUp_button);

            if(signUpMode){
                // if they are in sign up mode, then we should switch to the other view
                signUpMode = false;
                signUpButton.setText("Log In");
                loginTextView.setText("or, Sign Up");
            }else{
                // if they are not in sign up mode, then we should switch to the other view
                signUpMode = true;
                signUpButton.setText("Sign Up");
                loginTextView.setText("or, Log In");
            }
        }
    }

    public void onSignUpClicked(View view){
        // get access to the edit texts in the log in page
        EditText usernameEditText = findViewById(R.id.username_editText);
        EditText passwordEditText = findViewById(R.id.password_editText);


        // make sure that we have both a username and a password
        if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
            Toast.makeText(this, "A username and a password is required.", Toast.LENGTH_SHORT).show();
        } else{
            // if the username or the password is in there, then we set them up
            // this is the sign up call
            if(signUpMode){

            }else{
                // this means that they are in Log in mode, so we should log them in
            }
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_sign);


        // access the textview of log in
        loginTextView = findViewById(R.id.logIn_textView);
        loginTextView.setOnClickListener(this);

    }


}
