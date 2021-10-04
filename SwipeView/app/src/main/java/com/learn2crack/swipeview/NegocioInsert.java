package com.learn2crack.swipeview;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NegocioInsert extends AsyncTask<String,  Void, String> {

    private Products products;
    private Context context;

    public NegocioInsert(Context ct, Products Products1)
    {
        products = Products1;
        context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet flag = st.executeQuery("SELECT * FROM articulo WHERE id=" + products.getId());

            if(!flag.next()){
                ResultSet rs = st.executeQuery("SELECT id FROM categoria WHERE descripcion='" + products.getCategoryName() + "'");
                if(rs.first())
                {
                    products.setCategory(rs.getInt("id"));
                }
                int Resultado = st.executeUpdate("INSERT INTO articulo (id,nombre,stock,idcategoria)" + "VALUES (" + products.getId()+ "," + "'" + products.getProduct() + "'" + "," + products.getStock() + "," + products.getCategory() + ")");
                if(Resultado > 0)
                {
                    response = "Producto agregado correctamente";
                }
            } else return "El id ingresado ya se encuentra registrado";

        }
        catch(Exception e) {
            e.printStackTrace();
            response = "No se ha podido agregar el producto";
        }
        return response;
    }
    @Override
    protected void onPostExecute(String response) {
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
    }
}
