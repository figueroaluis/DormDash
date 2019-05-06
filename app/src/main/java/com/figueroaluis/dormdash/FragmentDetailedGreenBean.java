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
        greenBeanMenuItemsList.add(new MenuItem("Coffee","Hot"));
        greenBeanMenuItemsList.add(new MenuItem("Very Tasty Coffee","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Fucking Strong Coffee","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Meh Coffee","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Tasty Ass Juice","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Coffee","Hot"));
        greenBeanMenuItemsList.add(new MenuItem("Very Tasty Coffee","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Fucking Strong Coffee","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Meh Coffee","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Tasty Ass Juice","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Coffee","Hot"));
        greenBeanMenuItemsList.add(new MenuItem("Very Tasty Coffee","Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Fucking Strong Coffee","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Meh Coffee","Hot/Cold"));
        greenBeanMenuItemsList.add(new MenuItem("Tasty Ass Juice","Cold"));

        menuItemAdapter = new MenuItemAdapter(mContext, greenBeanMenuItemsList);
        mListView = view.findViewById(R.id.greenbean_menu_items_listview);
        mListView.setAdapter(menuItemAdapter);

        return view;
    }

    @Override
    public void onClick(View view){

    }

}
