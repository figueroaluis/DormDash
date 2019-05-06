package com.figueroaluis.dormdash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import cz.msebera.android.httpclient.client.CookieStore;
import okhttp3.Cookie;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import java.util.List;

public class FragmentHome extends Fragment implements View.OnClickListener {
    private AsyncHttpClient client;


    Button marketplaceButton;
    Button coolerButton;
    Button greenbeanButton;
    Button coffeecartButton;

    //show detailed marketplace menu

    public void showMarketplaceMenu() {

        FragmentDetailedMarketplace fdm = new FragmentDetailedMarketplace();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fdm, "findThisFragment")
                .addToBackStack(null)
                .commit();

    }

    public void showCoolerMenu() {

        FragmentDetailedCooler fdc = new FragmentDetailedCooler();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fdc, "findThisFragment")
                .addToBackStack(null)
                .commit();

    }

    public void showCoffeeCartMenu() {

        FragmentDetailedCoffeeCart fdcc = new FragmentDetailedCoffeeCart();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fdcc, "findThisFragment")
                .addToBackStack(null)
                .commit();

    }

    public void showGreenBeanMenu() {

        FragmentDetailedGreenBean fdgb = new FragmentDetailedGreenBean();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fdgb, "findThisFragment")
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.marketplace_button){
            //Toast.makeText(view.getContext(), "Marketplace button was pressed.", Toast.LENGTH_SHORT).show();
            showMarketplaceMenu();
        }else if(view.getId() == R.id.cooler_button){
            //Toast.makeText(view.getContext(), "Cooler button was pressed.", Toast.LENGTH_SHORT).show();
            showCoolerMenu();
        }else if(view.getId() == R.id.greenbean_button){
            //Toast.makeText(view.getContext(), "Green bean button was pressed.", Toast.LENGTH_SHORT).show();
            showGreenBeanMenu();
        } else if(view.getId() == R.id.coffeecart_button){
            //Toast.makeText(view.getContext(), "Coffee cart button was pressed.", Toast.LENGTH_SHORT).show();
            showCoffeeCartMenu();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        client = new AsyncHttpClient();

        /**EXAMPLE
//        PersistentCookieStore cookieStore = new PersistentCookieStore(getActivity());
//        List cookies = cookieStore.getCookies();
//        System.out.println("COOKIE SHIT" + cookies.get(0));
         **/




        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        marketplaceButton = view.findViewById(R.id.marketplace_button);
        marketplaceButton.setOnClickListener(this);
        coolerButton = view.findViewById(R.id.cooler_button);
        coolerButton.setOnClickListener(this);
        greenbeanButton = view.findViewById(R.id.greenbean_button);
        greenbeanButton.setOnClickListener(this);
        coffeecartButton = view.findViewById(R.id.coffeecart_button);
        coffeecartButton.setOnClickListener(this);


        return view;
    }
}
