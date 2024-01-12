package com.example.antons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button golvButton = findViewById(R.id.golvButton);
        Button kitchenButton = findViewById(R.id.kokButton);

        golvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, GolvActivity.class);
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