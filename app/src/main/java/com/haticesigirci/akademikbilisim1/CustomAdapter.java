package com.haticesigirci.akademikbilisim1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by haticesigirci on 01/02/16.
 */
public class CustomAdapter extends ArrayAdapter<DataModel> {

    private Context context;
    private int resource;
    private ArrayList<DataModel> items;


    public CustomAdapter(Context context,
                         int resource,
                         ArrayList<DataModel> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(resource, parent, false);


        TextView id = (TextView) view.findViewById(R.id.No);
        TextView adSoyad = (TextView) view.findViewById(R.id.ad);
        TextView sehir = (TextView) view.findViewById(R.id.sehir);

        id.setText(String.valueOf(items.get(position).id));
        adSoyad.setText(items.get(position).ad + " " + items.get(position).soyad);
        sehir.setText(items.get(position).sehir);

        return view;


    }
}
