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
        allMenuItemsList.add(new MenuItem("Salad","Four Leaf Cafe"));
        allMenuItemsList.add(new MenuItem("PB&J","Four Leaf Cafe"));
        allMenuItemsList.add(new MenuItem("Jamaican Jerk Sandwich","Four Leaf Cafe"));
        allMenuItemsList.add(new MenuItem("Mac n Cheese","Four Leaf Cafe"));
        allMenuItemsList.add(new MenuItem("Pasta","Four Leaf Cafe"));
        allMenuItemsList.add(new MenuItem("Fruit Salad","Four Leaf Cafe"));
        allMenuItemsList.add(new MenuItem("Cup Noodles",""));
        allMenuItemsList.add(new MenuItem("Hot Chocolate","Drink"));
        allMenuItemsList.add(new MenuItem("Coffee","Drink"));
        allMenuItemsList.add(new MenuItem("Tea","Drink"));
        allMenuItemsList.add(new MenuItem("Chips","Snack"));
        allMenuItemsList.add(new MenuItem("Donuts","Snack"));
        allMenuItemsList.add(new MenuItem("Pretzels","Snack"));
        allMenuItemsList.add(new MenuItem("Omelett","Breakfast"));
        allMenuItemsList.add(new MenuItem("Chef's Corner Sunday Brunch","Brunch"));
        allMenuItemsList.add(new MenuItem("Grill Breakfast","Breakfast"));
        allMenuItemsList.add(new MenuItem("Waffle Station","Breakfast"));
        allMenuItemsList.add(new MenuItem("Cereal","Breakfast"));
        allMenuItemsList.add(new MenuItem("Salad Bar","Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Taco Bar","Dinner"));
        allMenuItemsList.add(new MenuItem("Burger Bar","Dinner"));
        allMenuItemsList.add(new MenuItem("Breakfast Pastry","Breakfast"));
        allMenuItemsList.add(new MenuItem("Homestyle","Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Stir Fry Bar","Lunch"));
        allMenuItemsList.add(new MenuItem("Chef's Corner Options","Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Chicken Tenders","Dinner"));
        allMenuItemsList.add(new MenuItem("Grill Lunch Options","Lunch"));
        allMenuItemsList.add(new MenuItem("Fruit Bar","Breakfast/Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Muffin","Breakfast"));
        allMenuItemsList.add(new MenuItem("Chips","Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Other Market Plcae Snack","Breakfast/Lunch/Dinner"));
        allMenuItemsList.add(new MenuItem("Hot Coffee","Hot"));
        allMenuItemsList.add(new MenuItem("Iced Coffee","Cold"));
        allMenuItemsList.add(new MenuItem("Tea","Hot/Cold"));
        allMenuItemsList.add(new MenuItem("Pastry","Snack"));
        allMenuItemsList.add(new MenuItem("Izzy Soda","Cold"));
        allMenuItemsList.add(new MenuItem("Hot Coffee","Hot"));
        allMenuItemsList.add(new MenuItem("Iced Coffee","Cold"));
        allMenuItemsList.add(new MenuItem("Pastry","Snack"));
        allMenuItemsList.add(new MenuItem("Izzy Soda","Cold"));
        allMenuItemsList.add(new MenuItem("Burger","Order"));
        allMenuItemsList.add(new MenuItem("Pizza by the Slice","Pick Up"));
        allMenuItemsList.add(new MenuItem("Pizza to Order","Order"));
        allMenuItemsList.add(new MenuItem("Popcorn","Pick Up"));
        allMenuItemsList.add(new MenuItem("Protein Bar","Pick Up"));
        allMenuItemsList.add(new MenuItem("Starburst","Pick Up"));
        allMenuItemsList.add(new MenuItem("Twix","Pick Up"));
        allMenuItemsList.add(new MenuItem("Air Heads","Pick Up"));
        allMenuItemsList.add(new MenuItem("Slim Jim","Pick Up"));
        allMenuItemsList.add(new MenuItem("Chicken Sandwich","Order"));
        allMenuItemsList.add(new MenuItem("Philly Cheesesteak","Order"));
        allMenuItemsList.add(new MenuItem("Sushi","Order"));
        allMenuItemsList.add(new MenuItem("Fruit to Go","Pick Up"));
        allMenuItemsList.add(new MenuItem("Bagel","Order"));
        allMenuItemsList.add(new MenuItem("Snack Mix","Pick"));





        menuItemAdapter = new MenuItemAdapter(mContext, allMenuItemsList);
        mListView = view.findViewById(R.id.search_fragment_list_view);
        mListView.setAdapter(menuItemAdapter);



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
