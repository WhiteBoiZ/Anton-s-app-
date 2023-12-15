package com.example.antons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Dish> {

    public ListAdapter(Context context, int resource, List<Dish> objects){
        super(context, resource, objects);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = view.findViewById(R.id.spinnerText);
        textView.setText(getItem(position).getTitle());

        return view;
    }

}


