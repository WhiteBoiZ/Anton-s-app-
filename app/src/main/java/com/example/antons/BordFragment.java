package com.example.antons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class BordFragment extends Fragment {


    public static BordFragment newInstance(String table){
        BordFragment bordFragment = new BordFragment();
        Bundle args = new Bundle();
        args.putString("tableID",table);
        bordFragment.setArguments(args);
        return bordFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bord, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Använda getView().findViewById inom ett fragment
        Button addButton = view.findViewById(R.id.addbutton);
        //TextView fragmentID = view.findViewById(R.id.fragmentID);
        //fragmentID.setText(this.getArguments().getString("tableID"));

        Button addStarter = view.findViewById(R.id.addStarter);
        Button addMainCourse = view.findViewById(R.id.addMainCourse);
        Button addDessert = view.findViewById(R.id.addDessert);

        addStarter.setOnClickListener(this::onClick);
        addMainCourse.setOnClickListener(this::onClick);
        addDessert.setOnClickListener(this::onClick);

        /*
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Skapa och visa AddOrderFragment
                AddOrderFragment addOrderFragment = new AddOrderFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, addOrderFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });*/
    }


    public void onClick(View view){
        if(view.getId() == R.id.addStarter){
            System.out.println("Förrätt");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Förrätt", this.getArguments().getString("tableID"));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
        if(view.getId() == R.id.addMainCourse){
            System.out.println("Varmrätt");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Varmrätt", this.getArguments().getString("tableID"));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
        if(view.getId() == R.id.addDessert){
            System.out.println("Dessert");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Dessert", this.getArguments().getString("tableID"));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}