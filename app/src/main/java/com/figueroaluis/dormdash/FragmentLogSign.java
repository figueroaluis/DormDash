package com.figueroaluis.dormdash;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Button;
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

    Button acceptButton;

    private SharedPreferences mSharedPreferences;
    private String Name,Password;

    public static final String PREFERENCE = "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    public static final String PREF_SKIP_LOGIN = "skip_login";


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

            //SIGNUP MODE
            if(signUpMode){
//            client.post("http://3.14.49.112:80/register", params, new AsyncHttpResponseHandler() {
                client.post("http://10.0.2.2:80/register", params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        // called before request is started
                        System.out.println("STARTED sign up button");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //Test out the response with this
                        System.out.println("ONSUCCESS sign up");
                        String s = new String(responseBody);
                        System.out.println(s);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("failure sign up");
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });


            }else{
                //LOG IN
                // this means that they are in Log in mode, so we should log them in
                System.out.println("MADE IT LOGIN");



                client.post("http://10.0.2.2:80/login", params, new AsyncHttpResponseHandler() {
                    //                client.get("http://3.14.49.112:80/login", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        // called before request is started
                        System.out.println("STARTED Log in button");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        //Test out the response with this
                        System.out.println("ONSUCCESS log in");
                        String s = new String(responseBody);
                        String token = headers[0].getValue().substring(7);
                        System.out.println(s);
                        System.out.println(token);
                        if (token.startsWith("Bearer ")){

                            System.out.println(token);
                        } else {
                            //Error
                            System.out.println("fin");

                        }

                        AsyncHttpClient ahclient = new AsyncHttpClient();
                        PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
                        ahclient.setCookieStore(cookieStore);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        System.out.println("failure log in");
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

//        // sign up button
//        signUpButton = view.findViewById(R.id.signUp_button);
//        signUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onSignUpClicked(view);
//            }
//        });
//
//        // access the textview of log in
//        loginTextView = view.findViewById(R.id.logIn_textView);
//        loginTextView.setOnClickListener(this);
//
//        // get access to the edit texts in the log in page
//        usernameEditText = view.findViewById(R.id.username_editText);
//        passwordEditText = view.findViewById(R.id.password_editText);
//        ImageView logoImageView = view.findViewById(R.id.dormdash_logo);
//        ConstraintLayout backgroundLayout = view.findViewById(R.id.logSign_backgroundLayout);
//
//        // getting rid of the keyboard, clicking elsewhere
//        logoImageView.setOnClickListener(FragmentLogSign.this);
//        backgroundLayout.setOnClickListener(FragmentLogSign.this);
//
//        // if we click on enter on the keyboard and then disappear the keyboard
//        // it allows us to log in
//        passwordEditText.setOnKeyListener(FragmentLogSign.this);

        //---------------------------------------------------------------------
        //---------------------------------------------------------------------

        //implementing shared preferences

        mSharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        if(mSharedPreferences.contains(PREF_SKIP_LOGIN)) {
            View v = inflater.inflate(R.layout.fragment_accept_orders, container, false);

            acceptButton = (Button) view.findViewById(R.id.button_accept);
            acceptButton.setOnClickListener(this);
        }

        else {

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

            //sign up button
            signUpButton = view.findViewById(R.id.signUp_button);

            if(signUpMode) {

                Toast.makeText(getActivity().getApplicationContext(), "Sign up Button showing", Toast.LENGTH_SHORT).show();

                // sign up button
                signUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //onSignUpClicked(view);

                        if(validUserData()) {

                            if(mSharedPreferences.contains(PREF_NAME) && mSharedPreferences.contains(PREF_PASSWD)) {
                                SharedPreferences.Editor mEditor = mSharedPreferences.edit();

                                mEditor.putString(PREF_SKIP_LOGIN, "skip");
                                mEditor.commit();

                                //example code starts the intent of the login activity over, unsure what the fragment equivalent is

                            } else {

                                Toast.makeText(getActivity().getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            Toast.makeText(getActivity().getApplicationContext(), "Missing fields", Toast.LENGTH_SHORT).show();

                        }


                    }
                });

            } else {

                signUpButton.setOnClickListener(new View.OnClickListener()

                {
                        @Override
                        public void onClick (View view) {

                            Toast.makeText(getActivity().getApplicationContext(), "Log in Button showing", Toast.LENGTH_SHORT).show();

                            if(validUserData()) {

                                mSharedPreferences = getActivity().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                                SharedPreferences.Editor mEditor = mSharedPreferences.edit();

                                mEditor.putString(PREF_NAME, Name);
                                mEditor.putString(PREF_PASSWD, Password);
                                mEditor.commit();


                            }

                }
            });

            }



        }

        return view;
    }

    private boolean validUserData() {

        Name = usernameEditText.getText().toString().trim();
        Password = passwordEditText.getText().toString().trim();

        return !(Name.isEmpty() || Password.isEmpty());

    }


}