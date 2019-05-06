package com.figueroaluis.dormdash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.cookie.Cookie;

import java.lang.reflect.Array;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;


public class FragmentOrder extends Fragment implements View.OnClickListener {
    private AsyncHttpClient client;



    Button placeButton;
    TextView orderLabel;
    EditText orderText;
    EditText dropOffLocationText, pickUpLocationText;
    Spinner pickUpLocations, dropOffLocations;
    String token = null;

    String pickUpLocation, dropOffLocation;


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_placeButton){

            //orderText = (EditText) view.findViewById(R.id.editText_enterOrder);
            //pickUpLocationText = (EditText) view.findViewById(R.id.editText_pickUpLocation) ;


            /** CORRECT **/
            client = new AsyncHttpClient();
            PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());

            String cookieName = "";
            String cookieValue = "";
            String cookieUser = "";
            List<Cookie> cook = cookieStore.getCookies();
            for (Cookie c : cook) {
                if(c.getName().equals("token")) {
                    cookieName = c.getName();
                    cookieValue = c.getValue();
//                    cookieUser = c.getDomain().toString();
                } else{
                    cookieUser = c.getValue();
                }


//                cookieName = c.getName().toString();
//                cookieValue = c.getValue().toString();
//                cookieUser = c.getDomain().toString();
                System.out.println("here we are");
                System.out.println(cookieName);
                System.out.println(cookieValue);
                System.out.println(cookieUser);
            }

//            System.out.println("PICK UP LOCATION: " + pickUpLocation);
//            System.out.println("DROP OFF LOCATION: " + dropOffLocation);

            client.addHeader("Authorization", cookieValue);
            RequestParams params = new RequestParams();
            params.put("username", cookieUser);
            params.put("foodOrder", orderText.getText().toString().trim());
//            params.put("orderPickupLocation", pickUpLocationText.getText().toString().trim());
//            params.put("orderDropoffLocation", dropOffLocationText.getText().toString().trim());
            params.put("orderPickupLocation", pickUpLocation);
            params.put("orderDropoffLocation", dropOffLocation);

            //put drop off location
//            /** CORRECT **/
//            client = new AsyncHttpClient();
//            PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
//            List cookies = cookieStore.getCookies();
//            System.out.println("COOKIE SHIT" + cookies.get(0));
//            String str = cookies.get(0).toString();
//            System.out.println(str);




            client.post("http://3.14.202.131:80/order", params, new AsyncHttpResponseHandler() {

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

        orderText = view.findViewById(R.id.editText_enterOrder);

        pickUpLocations = view.findViewById(R.id.spinner_pickUpLocation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.pickuplocations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pickUpLocations.setAdapter(adapter);
        pickUpLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pickUpLocation = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dropOffLocations = view.findViewById(R.id.spinner_dropOffLocation);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getContext(), R.array.dropofflocations, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropOffLocations.setAdapter(adapter1);
        dropOffLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dropOffLocation = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        placeButton = (Button) view.findViewById(R.id.button_placeButton);
        placeButton.setOnClickListener(this);

        return view;

    }

}
