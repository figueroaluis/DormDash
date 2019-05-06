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
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));
        ccMenuItemsList.add(new MenuItem("Very Expensive PB&J","Four Leaf Cafe"));


        menuItemAdapter = new MenuItemAdapter(mContext, ccMenuItemsList);
        mListView = view.findViewById(R.id.coffeecart_menu_items_listview);
        mListView.setAdapter(menuItemAdapter);


        return view;

    }


}
