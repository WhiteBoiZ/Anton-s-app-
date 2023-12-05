package com.example.antons;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

public class GolvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.golv_main);

        // Sätt upp OnClickListener för varje bordknapp
        //BordFragment bord1 = new BordFragment();

        setupButtonClickListener(R.id.bord1Button, BordFragment.class);
        //setupButtonClickListener(R.id.bord2Button, Bord2Fragment.class);

        //setupButtonClickListener(R.id.addbutton, AddOrderFragment.class);



    }

    private void setupButtonClickListener(int buttonId, Class<? extends Fragment> fragmentClass) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Fragment fragment = fragmentClass.newInstance();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, fragment)
                            .addToBackStack(null)
                            .commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
