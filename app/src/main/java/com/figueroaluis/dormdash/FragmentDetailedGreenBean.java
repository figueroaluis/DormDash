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

public class FragmentDetailedGreenBean extends Fragment implements View.OnClickListener {

    private ListView mListView;
    private Context mContext;
    private MenuItemAdapter menuItemAdapter;
    private ArrayList<MenuItem> greenBeanMenuItemsList;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_greenbean, container, false);

        mContext = getContext();
        greenBeanMenuItemsList = new ArrayList<>();

        // dummy data for menu items in the marketplace
        greenBeanMenuItemsList.add(new MenuItem("Hot Coffee","Hot"));
        greenBeanMenuItemsList.add(new MenuItem("Iced Coffee","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Coffee with Espresso","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Tea","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Pastry","Snack"));
        greenBeanMenuItemsList.add(new MenuItem("Izzy Soda","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Hot Coffee","Hot"));
        greenBeanMenuItemsList.add(new MenuItem("Iced Coffee","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Coffee with Espresso","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Pastry","Snack"));
        greenBeanMenuItemsList.add(new MenuItem("Izzy Soda","Cold"));

        menuItemAdapter = new MenuItemAdapter(mContext, greenBeanMenuItemsList);
        mListView = view.findViewById(R.id.greenbean_menu_items_listview);
        mListView.setAdapter(menuItemAdapter);

        return view;
    }

    @Override
    public void onClick(View view){

    }

}
