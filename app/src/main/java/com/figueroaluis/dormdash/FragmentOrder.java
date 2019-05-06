package com.figueroaluis.dormdash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;

import java.util.List;


public class FragmentOrder extends Fragment implements View.OnClickListener  {
    private AsyncHttpClient client;



    Button placeButton;
    TextView orderLabel;
    EditText orderText;
    EditText pickUpLocationText;
    String token = null;


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_placeButton){

            //orderText = (EditText) view.findViewById(R.id.editText_enterOrder);
            //pickUpLocationText = (EditText) view.findViewById(R.id.editText_pickUpLocation) ;


            /** CORRECT **/
            client = new AsyncHttpClient();
            PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
            List cookies = cookieStore.getCookies();
            System.out.println("COOKIE SHIT" + cookies.get(0));
            String str = cookies.get(0).toString();
            System.out.println(str);

            RequestParams params = new RequestParams();
            params.put("username", "Sam");
            params.put("foodOrder", orderText.getText().toString().trim());
            params.put("orderPickupLocation", pickUpLocationText.getText().toString().trim());
            //put drop off location
//            /** CORRECT **/
//            client = new AsyncHttpClient();
//            PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
//            List cookies = cookieStore.getCookies();
//            System.out.println("COOKIE SHIT" + cookies.get(0));
//            String str = cookies.get(0).toString();
//            System.out.println(str);




            client.post("http://10.0.2.2:80/order", params, new AsyncHttpResponseHandler() {

                        @Override
                        public void onStart() {
                            // called before request is started
                            System.out.println("STARTED order onStart");

                        }
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            //Test out the response with this
                            System.out.println("ONSUCCESS orders");
                            String s = new String(responseBody);
                            System.out.println(s);


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            System.out.println("failure sign up");
                        }

                        @Override
                        public void onRetry(int retryNo) {
                            System.out.println("Retrying...");
                            // called when request is retried
                        }

                    });

            Toast.makeText(view.getContext(), "Placed Order", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /** CORRECT **/
        client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
        List cookies = cookieStore.getCookies();
//        System.out.println("PARSE" + java.net.HttpCookie.parse(cookies.get(0).toString()));
        System.out.println(cookies.get(0));
        System.out.println("FUCK");
        System.out.println(cookies.get(0).getClass().getName());

        String cookieName = "";
        String cookieValue = "";
        List<Cookie> cook = cookieStore.getCookies();
        for (Cookie c : cook) {
            cookieName = c.getName().toString();
            cookieValue = c.getValue().toString();
            System.out.println(cookieName);
            System.out.println(cookieValue);
        }

//        HttpCookie httpCookie = HttpCookie.parse(cookies.get(0)).get(0);
//
//        String target = cookies.get(0);

        // inflate the layout

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        orderText = (EditText) view.findViewById(R.id.editText_enterOrder);
        pickUpLocationText = (EditText) view.findViewById(R.id.editText_pickUpLocation);

        placeButton = (Button) view.findViewById(R.id.button_placeButton);
        placeButton.setOnClickListener(this);



        return view;

    }



}
