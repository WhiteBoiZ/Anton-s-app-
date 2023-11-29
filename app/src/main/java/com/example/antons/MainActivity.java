package com.example.antons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button golvButton = findViewById(R.id.golvButton);
        Button bord1Button = findViewById(R.id.bord1Button);
        Button bord2Button = findViewById(R.id.bord2Button);
        Button kitchenButton = findViewById(R.id.kokButton);
        golvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, GolvActivity.class);
                startActivity(intent);
            }
        });
        bord1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, Bord1Activity.class);
                startActivity(intent);
            }
        });
        bord2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, Bord2Activity.class);
                startActivity(intent);
            }
        });

        kitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KitchenActivity.class);
                startActivity(intent);
            }
        });


    }


}