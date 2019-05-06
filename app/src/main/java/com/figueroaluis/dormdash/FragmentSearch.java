package com.figueroaluis.dormdash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentSearch extends Fragment {

    private ListView mListView;
    private Context mContext;
    private MenuItemAdapter menuItemAdapter;
    private ArrayList<MenuItem> allMenuItemsList;
    private SearchView search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);


        mContext = getContext();
        allMenuItemsList = new ArrayList<>();
        search = view.findViewById(R.id.action_search);

        // dummy data for menu items in the marketplace
        allMenuItemsList.add(new MenuItem("Tasty Nachos (v)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Chicken of the Cave (v)","Lunch"));
        allMenuItemsList.add(new MenuItem("Sushi (v)(gf)","Dinner"));
        allMenuItemsList.add(new MenuItem("Salmon at the Grill (gf)(o)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Tasty Ass (v)(o)(gf)","Breakfast/Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Tasty Dick (v)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Chicken of the Ocean (v)","Lunch"));
        allMenuItemsList.add(new MenuItem("Meat Sushi (v)(gf)","Dinner"));
        allMenuItemsList.add(new MenuItem("Same Thing at the Grill (gf)(o)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Sandwich (v)(o)(gf)","Breakfast/Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Tasty Nachos (v)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Chicken of the Cave (v)","Lunch"));
        allMenuItemsList.add(new MenuItem("Sushi (v)(gf)","Dinner"));
        allMenuItemsList.add(new MenuItem("Salmon at the Grill (gf)(o)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Tasty Ass (v)(o)(gf)","Breakfast/Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Tasty Dick (v)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Chicken of the Ocean (v)","Lunch"));
        allMenuItemsList.add(new MenuItem("Meat Sushi (v)(gf)","Dinner"));
        allMenuItemsList.add(new MenuItem("Same Thing at the Grill (gf)(o)","Breakfast"));
        allMenuItemsList.add(new MenuItem("Sandwich (v)(o)(gf)","Breakfast/Lunch/Dinner"));

        menuItemAdapter = new MenuItemAdapter(mContext, allMenuItemsList);
        mListView = view.findViewById(R.id.search_fragment_list_view);
        mListView.setAdapter(menuItemAdapter);

//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                menuItemAdapter.getFilter().filter(s);
//                return false;
//            }
//        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.dashboard, menu);

        android.view.MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView search = (SearchView) searchItem.getActionView();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                menuItemAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

}
