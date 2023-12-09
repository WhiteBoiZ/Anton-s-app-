package com.example.antons;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddOrderFragment extends Fragment {



    private String type;

    private String table;



    private OnPassOrder onPassOrder;

    private OrderAdapter orderAdapter;


    public AddOrderFragment(String type, String table) {
        this.type = type;
        this.table = table;
    }

    public void setOnPassOrder(OnPassOrder onPassOrder) {
        this.onPassOrder = onPassOrder;
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
        Button finishOrderButton = view.findViewById(R.id.addToListButton);
        finishOrderButton.setOnClickListener(this::addToOrder);

        String[] starters = {"item1", "item2", "item3","item1", "item2", "item3","item1", "item2", "item3","item1", "item2", "item3"};
        String[] mainCourse = {"Lasagne", "Köttbullar"};
        String[] dessert = {"Glass", "Morotskaka"};
        ListAdapter starterListAdapter = new ListAdapter(requireContext(),R.layout.simple_spinner_item, Arrays.asList(starters));
        ListAdapter mainCourseAdapter = new ListAdapter(requireContext(),R.layout.simple_spinner_item, Arrays.asList(mainCourse));
        ListAdapter dessertAdapter = new ListAdapter(requireContext(),R.layout.simple_spinner_item, Arrays.asList(dessert));
        ListPopupWindow starterListPopupWindow = new ListPopupWindow(requireContext());
        ListPopupWindow maincourseListPopupWindow = new ListPopupWindow(requireContext());
        ListPopupWindow dessertListPopupWindow = new ListPopupWindow(requireContext());
        starterListPopupWindow.setAnchorView(view.findViewById(R.id.starterSelector));
        maincourseListPopupWindow.setAnchorView(view.findViewById(R.id.mainCourseSelector));
        dessertListPopupWindow.setAnchorView(view.findViewById(R.id.dessertSelector));
        starterListPopupWindow.setAdapter(starterListAdapter);
        maincourseListPopupWindow.setAdapter(mainCourseAdapter);
        dessertListPopupWindow.setAdapter(dessertAdapter);



        starterListPopupWindow.setHeight(200);
        maincourseListPopupWindow.setHeight(200);
        dessertListPopupWindow.setHeight(200);




        List<Order> orderList = new ArrayList<Order>(Arrays.asList(new Order("1","Fisk","15:50", "Varmrätt"),
                new Order("1","Kött","15:50", "Varmrätt")));

        this.orderAdapter = new OrderAdapter(orderList){

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


        starterListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = starterListAdapter.getItem(position);
                System.out.println(selectedItem);
                orderAdapter.add(new Order(table,selectedItem,"Tid","Förrätt"));
                starterListPopupWindow.dismiss();
            }
        });

        // Show the starters when the anchor view is clicked
        view.findViewById(R.id.starterSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starterListPopupWindow.show();
            }
        });

        maincourseListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = mainCourseAdapter.getItem(i);
                orderAdapter.add(new Order(table,selectedItem,"Tid","Varmrätt"));
                maincourseListPopupWindow.dismiss();
            }
        });

        view.findViewById(R.id.mainCourseSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincourseListPopupWindow.show();
            }
        });

        dessertListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = dessertAdapter.getItem(i);
                orderAdapter.add(new Order(table,selectedItem,"Tid","Efterrätt"));
                dessertListPopupWindow.dismiss();
            }
        });

        view.findViewById(R.id.dessertSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessertListPopupWindow.show();
            }
        });








        return view;
    }

    public interface OnPassOrder {
        void onDataPassed(List<Order> list);
    }

    private void passData(){
        if(onPassOrder != null){
            onPassOrder.onDataPassed(orderAdapter.getTableOrders());
        }
    }



    public void addToOrder(View view){
        //Pass the selected data in the orderList
        passData();
        System.out.println("Going back");
        this.getParentFragmentManager().popBackStack();
    }





}