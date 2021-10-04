package com.learn2crack.swipeview;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NegocioList extends AsyncTask<String,  Void, String> {

    private static String result2;
    private static ArrayList<Products> list = new ArrayList<Products>();
    private ListView lvArticulos;
    private Context context;

    public NegocioList(ListView lv, Context ct)
    {
        lvArticulos = lv;
        context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        Products Products;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select articulo.id,nombre,stock,categoria.descripcion from articulo inner join categoria on categoria.id = articulo.idcategoria order by articulo.id asc");
            list.clear();
            while(rs.next()) {
                Products = new Products();
                Products.setId(rs.getInt("id"));
                Products.setProduct(rs.getString("nombre"));
                Products.setStock(rs.getInt("stock"));
                Products.setCategoryName(rs.getString("categoria.descripcion"));
                list.add(Products);
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }
        return response;
    }
    @Override
    protected void onPostExecute(String response) {
        ListArticulosAdapter adapter = new ListArticulosAdapter(context, list);
        lvArticulos.setAdapter(adapter);
    }
}
