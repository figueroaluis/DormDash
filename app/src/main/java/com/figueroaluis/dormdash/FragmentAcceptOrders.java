package com.figueroaluis.dormdash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;
public class FragmentAcceptOrders extends Fragment implements View.OnClickListener  {
    private AsyncHttpClient client;


    Button acceptButton;
    String token = null;

    private ListView mListView;
    private Context mContext;
    private MenuItemAdapter menuItemAdapter;
    private ArrayList<MenuItem> menuItemsList;


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_accept){

            /** CORRECT **/
            client = new AsyncHttpClient();
            PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());

            String cookieName = "";
            String cookieValue = "";
            List<Cookie> cook = cookieStore.getCookies();
            for (Cookie c : cook) {
                cookieName = c.getName().toString();
                cookieValue = c.getValue().toString();
                System.out.println(cookieName);
                System.out.println(cookieValue);
            }


            RequestParams params = new RequestParams();
            params.put("username", "Sam");
            params.put("Authorization", cookieValue);
//            params.put("foodOrder", orderText.getText().toString());
//            params.put("orderPickupLocation", pickUpLocationText.getText().toString());

            client.post("http://3.14.202.131:80/order", params, new AsyncHttpResponseHandler() {
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

        //begin async thing
        client = new AsyncHttpClient();

        mContext = getContext();
        menuItemsList = new ArrayList<>();
        menuItemsList.add(new MenuItem("Omelett","Breakfast"));
        menuItemsList.add(new MenuItem("Chef's Corner Sunday Brunch","Brunch"));
        menuItemsList.add(new MenuItem("Grill Breakfast","Breakfast"));
        menuItemsList.add(new MenuItem("Waffle Station","Breakfast"));
        menuItemsList.add(new MenuItem("Cereal","Breakfast"));
        menuItemsList.add(new MenuItem("Salad Bar","Lunch/Dinner"));
        menuItemsList.add(new MenuItem("Spaghetti","Dinner"));
        menuItemsList.add(new MenuItem("Taco Bar","Dinner"));
        menuItemsList.add(new MenuItem("Burger Bar","Dinner"));

        RequestParams params = new RequestParams();


        client.get("http://3.14.202.131:80/feed", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                System.out.println("Begin Feed Retrieval");

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Test out the response with this
                System.out.println("Feed Pulled");
                String s = new String(responseBody);
                try {
                    JSONObject jsonObj = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Feed Failed");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });




        acceptButton = (Button) view.findViewById(R.id.button_accept);
        acceptButton.setOnClickListener(this);

        menuItemAdapter = new MenuItemAdapter(mContext, menuItemsList);
        mListView = view.findViewById(R.id.orders_list);
        mListView.setAdapter(menuItemAdapter);


        return view;

    }
}