package com.figueroaluis.dormdash;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//things for loopj/asynch http requests
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;


import static android.content.Context.INPUT_METHOD_SERVICE;


//begin asynch task library


public class FragmentLogSign extends Fragment implements View.OnClickListener, View.OnKeyListener{

    // check to see if we are in sign up or log in
    Boolean signUpMode = true;
    // buttons and textviews
    TextView loginTextView;
    EditText usernameEditText;
    EditText passwordEditText;
    AppCompatButton signUpButton;


    // implement a function that allows the button to open another view
    @Override
    public void onClick(View view){
        // check that it's the proper view
        if(view.getId() == R.id.logIn_textView){
            // log for us to keep track of it
            System.out.println(view.getId());
            System.out.println(R.id.logIn_textView);
            Log.i("Switch", "Was tapped");

            signUpButton = getView().findViewById(R.id.signUp_button);


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
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
        }
    }

    // method to sign up
    public void onSignUpClicked(View view){
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(getActivity());
        client.setCookieStore(myCookieStore);

        // make sure that we have both a username and a password
        if(usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")){
            Toast.makeText(view.getContext(), "A username and a password are required.", Toast.LENGTH_SHORT).show();
        } else{


            //sign up module
            RequestParams params = new RequestParams();
            params.put("username", usernameEditText.getText().toString());
            params.put("password", passwordEditText.getText().toString());
            if(signUpMode){


//            client.post("http://3.14.49.112:80/register", params, new AsyncHttpResponseHandler() {
                client.post("http://10.0.2.2:80/register", params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        // called before request is started
                        System.out.println("STARTED");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //Test out the response with this
                        System.out.println("ONSUCCESS");
                        String s = new String(responseBody);
                        System.out.println(s);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("failure");
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });


            }else{
                // this means that they are in Log in mode, so we should log them in
                System.out.println("MADE IT LOGIN");



                client.get("http://10.0.2.2:80/login", params, new AsyncHttpResponseHandler() {
                    //                client.get("http://3.14.49.112:80/login", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        // called before request is started
                        System.out.println("STARTED");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //Test out the response with this
                        System.out.println("ONSUCCESS");
                        String s = new String(responseBody);
                        System.out.println(s);


                        AsyncHttpClient ahclient = new AsyncHttpClient();
                        PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());

                        ahclient.setCookieStore(cookieStore);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("failure");
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });


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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.log_sign, container, false);

        // sign up button
        signUpButton = view.findViewById(R.id.signUp_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClicked(view);
            }
        });

        // access the textview of log in
        loginTextView = view.findViewById(R.id.logIn_textView);
        loginTextView.setOnClickListener(this);

        // get access to the edit texts in the log in page
        usernameEditText = view.findViewById(R.id.username_editText);
        passwordEditText = view.findViewById(R.id.password_editText);
        ImageView logoImageView = view.findViewById(R.id.dormdash_logo);
        ConstraintLayout backgroundLayout = view.findViewById(R.id.logSign_backgroundLayout);

        // getting rid of the keyboard, clicking elsewhere
        logoImageView.setOnClickListener(FragmentLogSign.this);
        backgroundLayout.setOnClickListener(FragmentLogSign.this);

        // if we click on enter on the keyboard and then disappear the keyboard
        // it allows us to log in
        passwordEditText.setOnKeyListener(FragmentLogSign.this);

        return view;
    }
}