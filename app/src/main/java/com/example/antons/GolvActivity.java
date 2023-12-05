package com.example.antons;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

public class GolvActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.golv_main);
        // Sätt upp OnClickListener för varje bordknapp

        //setupButtonClickListener(R.id.bord1Button, BordFragment.class);
        //setupButtonClickListener(R.id.bord2Button, BordFragment.class);
        Button tableButton1 = findViewById(R.id.bord1Button);
        tableButton1.setOnClickListener(this);
        Button tableButton2 = findViewById(R.id.bord2Button);
        tableButton2.setOnClickListener(this);
        Button tableButton3 = findViewById(R.id.bord3Button);
        tableButton3.setOnClickListener(this);
        Button tableButton4 = findViewById(R.id.bord4Button);
        tableButton4.setOnClickListener(this);
        Button tableButton5 = findViewById(R.id.bord5Button);
        tableButton5.setOnClickListener(this);
        Button tableButton6 = findViewById(R.id.bord6Button);
        tableButton6.setOnClickListener(this);
        Button tableButton7 = findViewById(R.id.bord7Button);
        tableButton7.setOnClickListener(this);
        Button tableButton8 = findViewById(R.id.bord8Button);
        tableButton8.setOnClickListener(this);



    }



    @Override
    public void onClick(View view){
        if (view.getId() == R.id.bord1Button) {
            System.out.println("Clicked: " + view.getTag().toString());
            //System.out.println(getStackCount());
            createFragment(view);
            //System.out.println(getStackCount());
            return;
        }
        if (view.getId() == R.id.bord2Button) {
            System.out.println("Clicked:" + view.getTag().toString());
            createFragment(view);
            return;
        }
        if (view.getId() == R.id.bord3Button) {
            System.out.println("Clicked: " + view.getTag().toString());
            createFragment(view);
            return;
        }
        if (view.getId() == R.id.bord4Button) {
            System.out.println("Clicked:" + view.getTag().toString());
            createFragment(view);
            return;
        }
        if (view.getId() == R.id.bord5Button) {
            System.out.println("Clicked: " + view.getTag().toString());
            createFragment(view);
            return;
        }
        if (view.getId() == R.id.bord6Button) {
            System.out.println("Clicked:" + view.getTag().toString());
            createFragment(view);
            return;
        }
        if (view.getId() == R.id.bord7Button) {
            System.out.println("Clicked: " + view.getTag().toString());
            createFragment(view);
            return;
        }
        if (view.getId() == R.id.bord8Button) {
            System.out.println("Clicked:" + view.getTag().toString());
            createFragment(view);
        }
    }


    public void createFragment(View view){
        try {
            Fragment fragment = BordFragment.newInstance(view.getTag().toString());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
    public int getStackCount() {
        BordFragment fragment = (BordFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (fragment != null) {
            return fragment.getChildFragmentManager().getBackStackEntryCount();
        }
        return 0;
    }*/

}
