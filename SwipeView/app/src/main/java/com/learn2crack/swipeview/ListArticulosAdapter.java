package com.learn2crack.swipeview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListArticulosAdapter extends ArrayAdapter<Products> {
    public ListArticulosAdapter(Context context, List<Products> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template, null);

        TextView id = (TextView) item.findViewById(R.id.id);
        TextView product = (TextView) item.findViewById(R.id.product);
        TextView stock = (TextView) item.findViewById(R.id.stock);
        TextView categoria = (TextView) item.findViewById(R.id.category);

        id.setText("ID: "+ getItem(position).getId()+"");
        product.setText("Producto: "+ getItem(position).getProduct()+"");
        stock.setText("Stock: "+ getItem(position).getStock()+"");
        categoria.setText("Categoria: " + getItem(position).getCategoryName());

        return item;
    }
}
