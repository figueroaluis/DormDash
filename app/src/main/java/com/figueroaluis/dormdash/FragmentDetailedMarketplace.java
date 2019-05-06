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

public class FragmentDetailedMarketplace extends Fragment implements View.OnClickListener {

    private ListView mListView;
    private Context mContext;
    private MenuItemAdapter menuItemAdapter;
    private ArrayList<MenuItem> mpMenuItemsList;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_marketplace, container, false);

        mContext = getContext();
        mpMenuItemsList = new ArrayList<>();

        // dummy data for menu items in the marketplace
        mpMenuItemsList.add(new MenuItem("Tasty Nachos (v)","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Chicken of the Cave (v)","Lunch"));
        mpMenuItemsList.add(new MenuItem("Sushi (v)(gf)","Dinner"));
        mpMenuItemsList.add(new MenuItem("Salmon at the Grill (gf)(o)","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Tasty Ass (v)(o)(gf)","Breakfast/Lunch/Dinner"));
        mpMenuItemsList.add(new MenuItem("Tasty Dick (v)","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Chicken of the Ocean (v)","Lunch"));
        mpMenuItemsList.add(new MenuItem("Meat Sushi (v)(gf)","Dinner"));
        mpMenuItemsList.add(new MenuItem("Same Thing at the Grill (gf)(o)","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Sandwich (v)(o)(gf)","Breakfast/Lunch/Dinner"));

        menuItemAdapter = new MenuItemAdapter(mContext, mpMenuItemsList);
        mListView = view.findViewById(R.id.marketplace_menu_items_listview);
        mListView.setAdapter(menuItemAdapter);

        return view;
    }

    @Override
    public void onClick(View view){

    }
}
