package com.figueroaluis.dormdash;


import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;

import static android.content.Context.INPUT_METHOD_SERVICE;

//things for loopj/asynch http requests


//begin asynch task library


public class FragmentLogSign extends Fragment implements View.OnClickListener, View.OnKeyListener{

    // check to see if we are in sign up or log in
    Boolean signUpMode = true;
    // buttons and textviews
    TextView loginTextView, usernameProfileTextView, customerType;
    EditText usernameEditText;
    EditText passwordEditText;
    AppCompatButton signUpButton;
    AppCompatButton logInButton;

    String uName;

    Button acceptButton, logoutButton;
    Switch workSwitch;

    private AsyncHttpClient client;
    private SharedPreferences mSharedPreferences;
    private String Name,Password;

    public static final String PREFERENCE = "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    public static final String PREF_SKIP_LOGIN = "skip_login";

    private ListView mListView;
    private Context mContext;
    private FragmentProfileAdapter adapter;
    private ArrayList<String> optionItemNames;
    private ArrayList<ProfileOptionItem> optionItemList;
    private ImageView userImage;



    // go to profile page
    public void showProfilePage(){
        FragmentProfile fragmentProfile = new FragmentProfile();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentProfile, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }


    // implement a function that allows the button to open another view
    @Override
    public void onClick(View view){
        // check that it's the proper view
        if(view.getId() == R.id.logIn_button){
            // log for us to keep track of it
            System.out.println(view.getId());
            System.out.println("Log In button clicked");

        } else if(view.getId() == R.id.dormdash_logo || view.getId() == R.id.logSign_backgroundLayout){
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
            try{
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        } else if(view.getId() == R.id.signUp_button) {
	            System.out.println(view.getId());
	            System.out.println("Sign Up button clicked");
        }
    }


    // method to sign up
    public void onSignUpClicked(View view){


        System.out.println("CLICKED YAY");

        client = new AsyncHttpClient();


        signUpButton = view.findViewById(R.id.signUp_button);

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



            }else {
                //LOG IN
                // this means that they are in Log in mode, so we should log them in
                System.out.println("LOG IN CLICKED YAY");


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
                      String token = headers[0].getValue();
//                        String token = "TEMPORARY";


                        /** CORRECT **/
                        PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
                        client.setCookieStore(cookieStore);
                        BasicClientCookie newCookie = new BasicClientCookie("token", token);
                        System.out.println(usernameEditText.getText().toString());
                        newCookie.setDomain(usernameEditText.getText().toString());

                        uName = usernameEditText.getText().toString();
                        BasicClientCookie nameCookie = new BasicClientCookie("name", uName);

                        System.out.println("TOKEN HERE: " + token);
                        System.out.println("USERNAME IN COOKIE");
                        cookieStore.addCookie(nameCookie);
                        cookieStore.addCookie(newCookie);


                        final SharedPreferences.Editor mEditor1 = mSharedPreferences.edit();
                        mEditor1.putString(PREF_NAME, usernameEditText.getText().toString());
                        mEditor1.putString(PREF_PASSWD, passwordEditText.getText().toString());
                        mEditor1.putString(PREF_SKIP_LOGIN, "skip");
                        mEditor1.commit();

//                        System.out.println("HERE BEFORE");

                        FragmentHome fragh = new FragmentHome();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, fragh, "Find this fragment")
                                .addToBackStack(null).commit();

//                        System.out.println("HERE AFTER");

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
      //  View view = inflater.inflate(R.layout.log_sign, container, false);

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
        final SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        if(mSharedPreferences.contains(PREF_SKIP_LOGIN)) {

            System.out.println("Recognized User");

            View v = inflater.inflate(R.layout.fragment_profile, container, false);

            optionItemNames = new ArrayList<>();
            optionItemList = new ArrayList<>();
            optionItemNames.add("Payment");
            optionItemNames.add("Help");
            optionItemNames.add("Settings");
            optionItemNames.add("About");
            optionItemNames.add("Log Out");

            workSwitch = v.findViewById(R.id.profile_option_icon);
            usernameProfileTextView = v.findViewById(R.id.profile_username_textview);
            customerType = v.findViewById(R.id.profile_usertype_textview);
            customerType.setText("Client");


            final String username = mSharedPreferences.getString(PREF_NAME, null);
            usernameProfileTextView.setText(username);


            for(int i = 0; i < optionItemNames.size(); i++){
                optionItemList.add(new ProfileOptionItem(optionItemNames.get(i)));
            }

            adapter = new FragmentProfileAdapter(getContext(), optionItemList);
            mListView = v.findViewById(R.id.option_items_listview);
            mListView.setAdapter(adapter);

            userImage = v.findViewById(R.id.profile_user_imageview);
            Picasso.get().load("https://i.kym-cdn.com/photos/images/newsfeed/001/487/781/ea0.jpg").into(userImage);

            workSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AsyncHttpClient client1 = new AsyncHttpClient();


                    RequestParams params = new RequestParams();
                    params.put("username", usernameProfileTextView.getText());
                    params.put("working", "0");
                    System.out.println(usernameProfileTextView.getText());

                    client1.post("http://10.0.2.2:80/worktime", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            System.out.println("ON SUCCESS as WORKER");
                            String s = new String(responseBody);
                            System.out.println(s);

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            System.out.println("failure to send worktime data initially");

                        }
                    });


                    if(workSwitch.isChecked()) {

                        customerType.setText("Worker");

                       // RequestParams params = new RequestParams();
                        params.put("username", usernameProfileTextView.getText());
                        params.put("working", "1");

                        client1.post("http://10.0.2.2:80/worktime", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                System.out.println("ON SUCCESS as WORKER");
                                String s = new String(responseBody);
                                System.out.println(s);

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                System.out.println("failure to set worker");

                            }
                        });

                    }

                    else {
                        customerType.setText("Client");

                       // RequestParams params = new RequestParams();
                        params.put("username", usernameProfileTextView.getText());
                        params.put("working", "0");

                        client1.post("http://10.0.2.2:80/worktime", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                System.out.println("ON SUCCESS as CLIENT");
                                String s = new String(responseBody);
                                System.out.println(s);

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                System.out.println("failure to set client");

                            }
                        });

                    }
                }
            });

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    ProfileOptionItem selectedOption = optionItemList.get(position);

                    if(selectedOption.text.equals("Log Out")){
                        // delete the shared preferences
                        // open up the new fragment that replaces the profile screen

                        mEditor.clear();
                        mEditor.commit();

                        //Toast.makeText(getActivity().getApplicationContext(),"Log out is clicked",Toast.LENGTH_SHORT).show();

                        FragmentLogSign fragls = new FragmentLogSign();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, fragls, "Find this fragment")
                                .addToBackStack(null).commit();


                    }
                }
            });

            return v;
        }

        else {

            View view = inflater.inflate(R.layout.log_sign, container, false);

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
            logInButton = view.findViewById(R.id.logIn_button);


            logInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        signUpMode = false;

                        if(validUserData()) {

                            if(mSharedPreferences.contains(PREF_NAME) && mSharedPreferences.contains(PREF_PASSWD)) {

                                onSignUpClicked(view);

                                mEditor.putString(PREF_SKIP_LOGIN, "skip");
                                mEditor.commit();

                                FragmentHome fragh = new FragmentHome();
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragh, "Find this fragment")
                                        .addToBackStack(null).commit();

                            } else {

                                onSignUpClicked(view);
                                //Toast.makeText(getActivity().getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            Toast.makeText(getActivity().getApplicationContext(), "Missing fields", Toast.LENGTH_SHORT).show();

                        }


                        //onSignUpClicked(view);



                    }
                });

                signUpButton.setOnClickListener(new View.OnClickListener()

                {
                        @Override
                        public void onClick (View view) {

                            signUpMode = true;


                            if(validUserData()) {

                                mSharedPreferences = getActivity().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                                SharedPreferences.Editor mEditor = mSharedPreferences.edit();

                                mEditor.putString(PREF_NAME, Name);
                                mEditor.putString(PREF_PASSWD, Password);
                                mEditor.commit(); //sign up the user's information in the shared preferences

                                Toast.makeText(getActivity().getApplicationContext(),"Sign up confirmed...Please log in", Toast.LENGTH_SHORT).show();


                            }


                            onSignUpClicked(view);


                }
            });



            return view;

        }

      //  return view;
    }

    private boolean validUserData() {

        Name = usernameEditText.getText().toString().trim();
        Password = passwordEditText.getText().toString().trim();

        return !(Name.isEmpty() || Password.isEmpty());

    }


}
