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

public class FragmentDetailedCoffeeCart extends Fragment implements View.OnClickListener {

    private ListView mListView;
    private Context mContext;
    private MenuItemAdapter menuItemAdapter;
    private ArrayList<MenuItem> ccMenuItemsList;

    @Override
    public void onClick(View view){
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_coffeecart, container, false);

        mContext = getContext();
        ccMenuItemsList = new ArrayList<>();

        // dummy data for menu items in the marketplace
        ccMenuItemsList.add(new MenuItem("Salad","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Jamaican Jerk Sandwich","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Mac n Cheese","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Pasta","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Fruit Salad","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Cup Noodles",""));
        ccMenuItemsList.add(new MenuItem("Hot Chocolate","Drink"));
        ccMenuItemsList.add(new MenuItem("Coffee","Drink"));
        ccMenuItemsList.add(new MenuItem("Tea","Drink"));
        ccMenuItemsList.add(new MenuItem("Chips","Snack"));
        ccMenuItemsList.add(new MenuItem("Donuts","Snack"));
        ccMenuItemsList.add(new MenuItem("Pretzels","Snack"));

        menuItemAdapter = new MenuItemAdapter(mContext, ccMenuItemsList);
        mListView = view.findViewById(R.id.coffeecart_menu_items_listview);
        mListView.setAdapter(menuItemAdapter);


        return view;

    }


}
