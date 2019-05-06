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
        mpMenuItemsList.add(new MenuItem("Omelett","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Chef's Corner Sunday Brunch","Brunch"));
        mpMenuItemsList.add(new MenuItem("Grill Breakfast","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Waffle Station","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Cereal","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Salad Bar","Lunch/Dinner"));
        mpMenuItemsList.add(new MenuItem("Spaghetti","Dinner"));
        mpMenuItemsList.add(new MenuItem("Taco Bar","Dinner"));
        mpMenuItemsList.add(new MenuItem("Burger Bar","Dinner"));
        mpMenuItemsList.add(new MenuItem("Breakfast Pastry","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Homestyle","Lunch/Dinner"));
        mpMenuItemsList.add(new MenuItem("Stir Fry Bar","Lunch"));
        mpMenuItemsList.add(new MenuItem("Chef's Corner Options","Lunch/Dinner"));
        mpMenuItemsList.add(new MenuItem("Chicken Tenders","Dinner"));
        mpMenuItemsList.add(new MenuItem("Grill Lunch Options","Lunch"));
        mpMenuItemsList.add(new MenuItem("Fruit Bar","Breakfast/Lunch/Dinner"));
        mpMenuItemsList.add(new MenuItem("Muffin","Breakfast"));
        mpMenuItemsList.add(new MenuItem("Chips","Lunch/Dinner"));
        mpMenuItemsList.add(new MenuItem("Other Market Place Snack","Breakfast/Lunch/Dinner"));

        menuItemAdapter = new MenuItemAdapter(mContext, mpMenuItemsList);
        mListView = view.findViewById(R.id.marketplace_menu_items_listview);
        mListView.setAdapter(menuItemAdapter);

        return view;
    }

    @Override
    public void onClick(View view){

    }
}
