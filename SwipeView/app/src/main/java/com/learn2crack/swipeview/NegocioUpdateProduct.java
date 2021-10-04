package com.learn2crack.swipeview;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NegocioUpdateProduct extends AsyncTask<String,  Void, String>

    {

        private Products products;
        private Context context;

    public NegocioUpdateProduct(Context ct, Products Products1)
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

            if(flag.next()){
                ResultSet rs = st.executeQuery("SELECT id FROM categoria WHERE descripcion='" + products.getCategoryName() + "'");
                if(rs.first())
                {
                    products.setCategory(rs.getInt("id"));
                }
                int Resultado = st.executeUpdate("UPDATE articulo set nombre= '" + products.getProduct() + "'" + ", stock=" + products.getStock() + ",idcategoria=" + products.getCategory() + " WHERE id=" + products.getId());
                if(Resultado > 0)
                {
                    response = "Se ha actualizado el producto";
                }
            } else return "No se ha podido actualizar el producto";

        }
        catch(Exception e) {
            e.printStackTrace();
            response = "No se ha podido actualizar el producto";
        }
        return response;
    }
        @Override
        protected void onPostExecute(String response) {
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
    }
}
