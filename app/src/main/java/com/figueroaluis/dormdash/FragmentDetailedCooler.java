package com.figueroaluis.dormdash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentDetailedCooler extends Fragment implements View.OnClickListener {

    private ListView mListView;
    private Context mContext;
    private MenuItemAdapter menuItemAdapter;
    private ArrayList<MenuItem> coolerMenuItemsList;

    @Override
    public void onClick(View view){

    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cooler, container, false);

        mContext = getContext();
        coolerMenuItemsList = new ArrayList<>();

        // dummy data for menu items in the marketplace
        coolerMenuItemsList.add(new MenuItem("Burger","Order"));
        coolerMenuItemsList.add(new MenuItem("Pizza by the Slice","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Pizza to Order","Order"));
        coolerMenuItemsList.add(new MenuItem("Popcorn","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Protein Bar","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Starburst","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Twix","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Air Heads","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Slim Jim","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Chicken Sandwich","Order"));
        coolerMenuItemsList.add(new MenuItem("Philly Cheesesteak","Order"));
        coolerMenuItemsList.add(new MenuItem("Sushi","Order"));
        coolerMenuItemsList.add(new MenuItem("Fruit to Go","Pick Up"));
        coolerMenuItemsList.add(new MenuItem("Bagel","Order"));
        coolerMenuItemsList.add(new MenuItem("Snack Mix","Pick"));



        menuItemAdapter = new MenuItemAdapter(mContext, coolerMenuItemsList);
        mListView = view.findViewById(R.id.cooler_menu_items_listview);
        mListView.setAdapter(menuItemAdapter);

        return view;

    }


}
