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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    public static final String TITLE = "MODIFICACION";
    EditText id, product, stock;
    Button modificar, buscar;
    Spinner category;
    String Id;
    Products Products;
    protected ArrayAdapter<CharSequence> adapter;
    public static ProfileFragment newInstance() {

        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        modificar = (Button) v.findViewById(R.id.Agregar);
        buscar = (Button) v.findViewById(R.id.Buscar);
        id = (EditText) v.findViewById(R.id.Id);
        product = (EditText) v.findViewById(R.id.Producto);
        stock = (EditText) v.findViewById(R.id.Stock);
        category = (Spinner) v.findViewById(R.id.Categoria);
        Connect2();
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Id = id.getText().toString();
                if(Id.equals(""))
                {
                    Toast.makeText(getContext(), "Para buscar es obligatorio ingresar un ID",Toast.LENGTH_SHORT).show();
                }
                else {
                        Connect();
                }
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String Id = id.getText().toString();
                String Product = product.getText().toString();
                String Stock = stock.getText().toString();
                String Category = category.getSelectedItem().toString();
                if(Id.equals("")||Product.equals("")||Stock.equals(""))
                {
                    Toast.makeText(getContext(), "Complete todos los campos",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Products = new Products();
                    Products.setId(Integer.parseInt(Id));
                    Products.setProduct(Product);
                    Products.setStock(Integer.parseInt(Stock));
                    Products.setCategoryName(Category);
                    Connect3();
                }
            }
        });
        return v;
    }
    public void Connect2() {
        NegocioDropDown2 task = new NegocioDropDown2(category,getContext());
        task.execute();
    }
    public void Connect() {
        NegocioSearchProduct task = new NegocioSearchProduct(getContext(),Id,id,product,stock,category);
        task.execute();
    }
    public void Connect3() {
        NegocioUpdateProduct task = new NegocioUpdateProduct(getContext(),Products);
        task.execute();
    }
}