package com.figueroaluis.dormdash;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //tester onClick to open up the sign in page
    @Override
    public void onClick(View view){
        if(view.getId() == R.id.imageButton_profile){
            // log for us to check
            Log.i("Open Sign Up", "Was pressed");
            Intent intent = new Intent(MainActivity.this, LogSign.class);
            startActivity(intent);
        } else if(view.getId() == R.id.imageButton_orders){
            // open the screen to make an order
            Log.i("Order page button", "Was pressed");
            Intent intent = new Intent(MainActivity.this, OrderScreen.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.imageButton_search){
            // open the screen to make an order
            Log.i("Search page button", "Was pressed");
            Intent intent = new Intent(MainActivity.this, SearchScreen.class);
            startActivity(intent);
        }
    }

    ImageButton marketPlaceButton;
    ImageButton coolerButton;
    ImageButton greenBeanButton;
    ImageButton coffeeCartButton;
    ImageButton homeButton;
    ImageButton searchButton;
    ImageButton ordersButton;
    ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marketPlaceButton = (ImageButton) findViewById(R.id.imageButton_marketplace);
        marketPlaceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //method for what happens when you click, now just text appears
                Toast.makeText(MainActivity.this, "Marketplace button works",
                        Toast.LENGTH_SHORT).show();
            }
        });

        coolerButton = (ImageButton) findViewById(R.id.imageButton_cooler);
        coolerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //method for what happens when you click, now just text appears
                Toast.makeText(MainActivity.this, "Cooler button works",
                        Toast.LENGTH_SHORT).show();
            }
        });

        greenBeanButton = (ImageButton) findViewById(R.id.imageButton_greenbean);
        greenBeanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //method for what happens when you click, now just text appears
                Toast.makeText(MainActivity.this, "Green Bean button works",
                        Toast.LENGTH_SHORT).show();
            }
        });

        coffeeCartButton = (ImageButton) findViewById(R.id.imageButton_coffeecart);
        coffeeCartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //method for what happens when you click, now just text appears
                Toast.makeText(MainActivity.this, "Coffee Cart button works",
                        Toast.LENGTH_SHORT).show();
            }
        });

        homeButton = (ImageButton) findViewById(R.id.imageButton_homebutton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //method for what happens when you click, now just text appears
                Toast.makeText(MainActivity.this, "This is the home screen",
                        Toast.LENGTH_SHORT).show();
            }
        });


        ordersButton = (ImageButton) findViewById(R.id.imageButton_orders);
        ordersButton.setOnClickListener(MainActivity.this);

        profileButton = (ImageButton) findViewById(R.id.imageButton_profile);
        profileButton.setOnClickListener(MainActivity.this);

        searchButton = (ImageButton) findViewById(R.id.imageButton_search);
        searchButton.setOnClickListener(MainActivity.this);

    }


    // to do
    // need to implement a feature in which we have a constant bar at the bottom
    // if we change the screen, it'll remain at the bottom regardless of whatever layout
    // and as we scroll, the bottom bar will stay in place
}
