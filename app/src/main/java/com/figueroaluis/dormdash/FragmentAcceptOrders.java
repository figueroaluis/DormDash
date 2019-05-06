package com.figueroaluis.dormdash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FragmentAcceptOrders extends Fragment implements View.OnClickListener  {
    private AsyncHttpClient client;


    Button acceptButton;
    String token = null;


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_accept){

            /** CORRECT **/
            client = new AsyncHttpClient();
            PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
            List cookies = cookieStore.getCookies();
            System.out.println("COOKIE SHIT" + cookies.get(0));
            String str = cookies.get(0).toString();
            System.out.println(str);

            RequestParams params = new RequestParams();
            params.put("username", "Sam");
//            params.put("foodOrder", orderText.getText().toString());
//            params.put("orderPickupLocation", pickUpLocationText.getText().toString());

            client.post("http://10.0.2.2:80/order", params, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    // called before request is started
                    System.out.println("STARTED order onStart");

                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    System.out.println("ONSUCCESS orders accepting");
                    String s = new String(responseBody);
                    System.out.println(s);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    System.out.println("Accept orders failure");

                }
            });

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_accept_orders, container, false);


        acceptButton = (Button) view.findViewById(R.id.button_accept);
        acceptButton.setOnClickListener(this);


        return view;

    }
}