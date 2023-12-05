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
        TextView fragmentID = view.findViewById(R.id.fragmentID);
        fragmentID.setText(this.getArguments().getString("tableID"));
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
        });
    }
}