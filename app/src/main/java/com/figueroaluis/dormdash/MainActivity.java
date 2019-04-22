package com.figueroaluis.dormdash;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;


public class MainActivity extends AppCompatActivity {

    // Navigation bar stuff
    AHBottomNavigation bottomNavigation;
    AHBottomNavigationItem homeButton_navBar;
    AHBottomNavigationItem searchButton_navBar;
    AHBottomNavigationItem ordersButton_navBar;
    AHBottomNavigationItem profile_navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bottom view stuff
        bottomNavigation = findViewById(R.id.bottom_navigation);
        homeButton_navBar = new AHBottomNavigationItem("Home", R.drawable.home_icon);
        searchButton_navBar = new AHBottomNavigationItem("Search",R.drawable.search_icon);
        ordersButton_navBar = new AHBottomNavigationItem("Orders",R.drawable.orders_icon);
        profile_navBar = new AHBottomNavigationItem("Profile",R.drawable.profile_icon);
        bottomNavigation.addItem(homeButton_navBar);
        bottomNavigation.addItem(searchButton_navBar);
        bottomNavigation.addItem(ordersButton_navBar);
        bottomNavigation.addItem(profile_navBar);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setAccentColor(Color.parseColor("#F5681E"));
        bottomNavigation.setForceTint(true);
        bottomNavigation.setOnTabSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

    }


    private AHBottomNavigation.OnTabSelectedListener navListener =
            new AHBottomNavigation.OnTabSelectedListener() {
                @Override
                public boolean onTabSelected(int position, boolean wasSelected) {
                    // position tells you which tab was selected
                    // wasSelected tells you if the tab is currently selected
                    System.out.println(position);
                    System.out.println(wasSelected);
                    Fragment selectedFragment = null;
                    if(position==0) {
                        selectedFragment = new FragmentHome();
                    }else if(position==1){
                        selectedFragment = new FragmentSearch();
                    } else if(position==2){
                        // open the sign up page
                    } else if(position==3){
                        // open the log/sign page
                        selectedFragment = new FragmentLogSign();
                    } else{
                        Log.i("BOTTOM NAVIGATION BAR","Bug: one of the tabs is somehow not clicked");
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }

            };

}

