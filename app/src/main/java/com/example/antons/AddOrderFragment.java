package com.example.antons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<Order> orderList = new ArrayList<Order>(Arrays.asList(new Order("1","Fisk","15:50", "Varmrätt"),
                new Order("1","Kött","15:50", "Varmrätt")));

        OrderAdapter orderAdapter = new OrderAdapter(orderList){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.add_order, parent, false);

                return new ViewHolder(view);

            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position){
                Order order = orderList.get(position);
                holder.getOrder().setText(order.getOrder());
                holder.setType(holder.itemView.findViewById(R.id.typeText));
                holder.getType().setText(order.getType());
            }
        };

        RecyclerView selectedView = view.findViewById(R.id.selectedOrders);
        selectedView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        selectedView.setAdapter(orderAdapter);



        //Shows currently selected table.
        TextView textView = view.findViewById(R.id.preOrderText);
        textView.setText(this.type);


        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = listAdapter.getItem(position);
                System.out.println(selectedItem);
                orderAdapter.add(new Order(table,selectedItem,"Tid","Typ"));
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