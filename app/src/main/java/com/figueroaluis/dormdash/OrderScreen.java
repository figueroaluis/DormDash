package com.figueroaluis.dormdash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OrderScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);

        Button placeButton;

        placeButton = (Button) findViewById(R.id.placebutton);
        placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ordertext = (EditText) findViewById(R.id.orderedittext);

                String orderString = ordertext.getText().toString();

                Toast.makeText(OrderScreen.this, orderString,
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}
