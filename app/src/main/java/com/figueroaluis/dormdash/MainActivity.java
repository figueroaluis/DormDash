package com.figueroaluis.dormdash;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.cookie.Cookie;


public class MainActivity extends AppCompatActivity {

    // Navigation bar stuff
    AHBottomNavigation bottomNavigation;
    AHBottomNavigationItem homeButton_navBar;
    AHBottomNavigationItem searchButton_navBar;
    AHBottomNavigationItem ordersButton_navBar;
    AHBottomNavigationItem profile_navBar;
    SharedPreferences mSharedPreferences;

    private boolean switchMode;
    private AsyncHttpClient client;
    private boolean status;

    @Subscribe
    public void onEvent(FragmentProfile.switchBoolean event){
        if(event.isWorkerModeOn()){
            switchMode = true;
        }else{
            switchMode = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

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


                    //begin async thing
                    client = new AsyncHttpClient();
                    PersistentCookieStore cookieStore = new PersistentCookieStore(getApplicationContext());
                    //grab creds from cookies
                    String cookieName = "";
                    String cookieToken = "";
                    String cookieUser = "";
                    List<Cookie> cook = cookieStore.getCookies();
                    for (Cookie c : cook) {
                        if (c.getName().equals("token")) {
                            cookieName = c.getName();
                            cookieToken = c.getValue();
//                    cookieUser = c.getDomain().toString();
                        } else {
                            cookieUser = c.getValue();
                        }
                    }
                    System.out.print("The cookieuser is "+ cookieUser);
                    System.out.print("The cookietokenis " + cookieToken);


                    RequestParams params = new RequestParams();
                    params.add("username", cookieUser);
                    client.addHeader("Authorization", cookieToken);
                    System.out.println("TOKEN IS" + cookieToken);
                    System.out.println("USER IS" + cookieUser);


                        client.post("http://10.0.2.2:80/status", params, new AsyncHttpResponseHandler() {
                        //                client.get("http://3.14.49.112:80/login", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onStart() {
                            // called before request is started
                            System.out.println("Status");

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            //Test out the response with this
                            System.out.println("ONSUCCESS STATUS CHECK");
                            String s = new String(responseBody);

                            System.out.println("THIS IS THE RESPONSE BODY" + s);
                            if (s.equals("y")) switchMode = true;
                            else switchMode = false;


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            System.out.println("failure STATUS!!!");
                        }

                        @Override
                        public void onRetry(int retryNo) {
                            // called when request is retried
                        }
                    });

                    // position tells you which tab was selected
                    // wasSelected tells you if the tab is currently selected
                    System.out.println(position);
                    System.out.println(wasSelected);
                    System.out.println("This is the boolean for switch mode in MainAcvity: " + switchMode);
                    Fragment selectedFragment = null;
                    if(position==0) {
                        selectedFragment = new FragmentHome();
                    }else if(position==1){
                        selectedFragment = new FragmentSearch();
                    } else if(position==2 && !switchMode){
                        // open the sign up page
                        selectedFragment = new FragmentOrder();
                    } else if(position==2 && switchMode) {
                        selectedFragment = new FragmentAcceptOrders();
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

    public void Webscrape() {
        Document doc;
        Elements foods = new Elements();

        try {
            doc = Jsoup.connect("https://www.oxy.edu/student-life/campus-dining/marketplace").get();

            //Jsoup connect gets HTML code from website, but parse gets words

            //doc = Jsoup.parse("https://www.oxy.edu/student-life/campus-dining/marketplace");

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
            String formattedDate = (dateFormat.format(date));

            String title = doc.title();
            System.out.println("Title: " + title);

            Elements lines = doc.select("p");
            for(Element line : lines) {
                if(line.text().contains(formattedDate)) {		//add formattedDate
                    System.out.println("Foods: " + line.text());
                    System.out.println(line.nextElementSibling().text());
                    System.out.println(line.nextElementSibling().nextElementSibling().text());
                    System.out.println(line.nextElementSibling().nextElementSibling().nextElementSibling().text());
                    System.out.println(line.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text());
                }
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // unregister the event
        EventBus.getDefault().unregister(this);
    }


}

