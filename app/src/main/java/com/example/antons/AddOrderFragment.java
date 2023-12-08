package com.example.antons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddOrderFragment extends Fragment {


    private String type;

    private String table;

    public AddOrderFragment(String type, String table) {
        this.type = type;
        this.table = table;
    }

    public static AddOrderFragment newInstance(String type, String table) {
        AddOrderFragment fragment = new AddOrderFragment(type,table);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_order, container, false);



        String[] starters = {"item1", "item2", "item3","item1", "item2", "item3","item1", "item2", "item3","item1", "item2", "item3"};
        ListAdapter listAdapter = new ListAdapter(requireContext(),R.layout.simple_spinner_item, Arrays.asList(starters));
        ListPopupWindow listPopupWindow = new ListPopupWindow(requireContext());
        listPopupWindow.setAnchorView(view.findViewById(R.id.starterSelector));
        listPopupWindow.setAdapter(listAdapter);
        listPopupWindow.setHeight(200);


        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = listAdapter.getItem(position);
                listPopupWindow.dismiss();
            }
        });

        // Show the ListPopupWindow when the anchor view is clicked
        view.findViewById(R.id.starterSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.show();
            }
        });



        return view;
    }
}