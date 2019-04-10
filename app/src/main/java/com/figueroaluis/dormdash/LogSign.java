package com.figueroaluis.dormdash;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LogSign extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{

    // check to see if we are in sign up or log in
    Boolean signUpMode = true;
    // buttons and textviews
    TextView loginTextView;
    EditText usernameEditText;
    EditText passwordEditText;


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
        } else if(view.getId() == R.id.dormdash_logo || view.getId() == R.id.logSign_backgroundLayout){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    // method to sign up
    public void onSignUpClicked(View view){
        // make sure that we have both a username and a password
        if(usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")){
            Toast.makeText(LogSign.this, "A username and a password are required.", Toast.LENGTH_SHORT).show();
        } else{
            // if the username or the password is in there, then we set them up
            // this is the sign up call


            //add registered user data to database
//            InsertData data = new InsertData();
//            data.registerUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());


            //Toast.makeText(LogSign.this, "A username and a password have been given.", Toast.LENGTH_SHORT).show();
            if(signUpMode){
                ServerConnect serverConnect = new ServerConnect();
                serverConnect.execute("user", "password");



            }else{
                // this means that they are in Log in mode, so we should log them in
                System.out.println("MADE IT LOGIN");

//                ServerConnect serverConnect = new ServerConnect();
//                Toast.makeText(LogSign.this, message, Toast.LENGTH_SHORT).show();




            }
        }

    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent){
        if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            onSignUpClicked(view);
        }
        return false;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_sign);


        // access the textview of log in
        loginTextView = findViewById(R.id.logIn_textView);
        loginTextView.setOnClickListener(this);
        // get access to the edit texts in the log in page
        usernameEditText = findViewById(R.id.username_editText);
        passwordEditText = findViewById(R.id.password_editText);
        ImageView logoImageView = findViewById(R.id.dormdash_logo);
        ConstraintLayout backgroundLayout = findViewById(R.id.logSign_backgroundLayout);
        // getting rid of the keyboard, clicking elsewhere
        logoImageView.setOnClickListener(LogSign.this);
        backgroundLayout.setOnClickListener(LogSign.this);

        // if we click on enter on the keyboard and then disappear the keyboard
        // it allows us to log in
        passwordEditText.setOnKeyListener(LogSign.this);


    }



}
