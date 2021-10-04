package com.learn2crack.swipeview;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mysql.fabric.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NegocioSearchProduct extends AsyncTask<String,  Void, String> {
    private Products products;
    private Context context;
    private String id;
    private Spinner lvCategory;
    private EditText idproduct,product,stock;
    ArrayAdapter<String> comboAdapter;

    public NegocioSearchProduct(Context ct, String Id, EditText IdProduct, EditText Product, EditText Stock, Spinner Category)
    {
        id = Id;
        context = ct;
        idproduct = IdProduct;
        product = Product;
        stock = Stock;
        lvCategory = Category;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select articulo.id,nombre,stock,categoria.id from articulo inner join categoria on categoria.id = articulo.idcategoria WHERE articulo.id=" + id);
                if(rs.next())
                {
                    products = new Products();
                    products.setId(rs.getInt("articulo.id"));
                    products.setProduct(rs.getString("nombre"));
                    products.setStock(rs.getInt("stock"));
                    products.setCategory(rs.getInt("categoria.id"));
                    response = "Se encontro un producto";
                }
                else
                {
                    response = "No se ha encontrado ningun producto";
                }
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "No se ha encontrado ningun producto";
        }
        return response;
    }
    @Override
    protected void onPostExecute(String response) {
        if(response == "Se encontro un producto")
        {
            if(products != null)
            {
                product.setText(products.getProduct());
                stock.setText(products.getStock().toString());
                idproduct.setText(products.getId().toString());
                int selectionPosition= products.getCategory();
                lvCategory.setSelection(selectionPosition-1);
            }
        }
        else {
            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
        }
    }
}

