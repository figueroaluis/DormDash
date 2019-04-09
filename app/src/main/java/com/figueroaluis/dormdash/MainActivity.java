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
                Toast.makeText(MainActivity.this, "Home button works",
                        Toast.LENGTH_SHORT).show();
            }
        });

        searchButton = (ImageButton) findViewById(R.id.imageButton_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //method for what happens when you click, now just text appears
                Toast.makeText(MainActivity.this, "Search button works",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ordersButton = (ImageButton) findViewById(R.id.imageButton_orders);
        ordersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //method for what happens when you click, now just text appears
                Toast.makeText(MainActivity.this, "Orders button works",
                        Toast.LENGTH_SHORT).show();
            }
        });

        profileButton = (ImageButton) findViewById(R.id.imageButton_profile);
        profileButton.setOnClickListener(MainActivity.this);

    }
}
