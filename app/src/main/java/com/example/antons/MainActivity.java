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
        Button golvButton = findViewById(R.id.GolvButton);
        golvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, GolvActivity.class);
                startActivity(intent);
            }
        });


    }


}