package com.learn2crack.swipeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static final String TITLE = "ALTA";
    EditText id, product, stock;
    Button agregar;
    Spinner category;
    Products Products;
    protected ArrayAdapter<CharSequence> adapter;
    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        id = (EditText) v.findViewById(R.id.Id);
        product = (EditText) v.findViewById(R.id.Producto);
        stock = (EditText) v.findViewById(R.id.Stock);
        agregar = (Button) v.findViewById(R.id.Agregar);
        category = (Spinner) v.findViewById(R.id.Categoria);
        Connect2();
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().equals("")|| product.getText().toString().equals("")||stock.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Complete todos los campos",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Products = new Products();
                    Products.setId(Integer.parseInt(id.getText().toString()));
                    Products.setProduct(product.getText().toString());
                    Products.setStock(Integer.parseInt(stock.getText().toString()));
                    Products.setCategoryName(category.getSelectedItem().toString());
                    Connect();
                }
            }
        });
        return v;
    }
    public void Connect() {
        NegocioInsert task = new NegocioInsert(getContext(), Products);
        task.execute();
    }
    public void Connect2() {
        NegocioDropDown task = new NegocioDropDown(category,getContext());
        task.execute();
    }

}