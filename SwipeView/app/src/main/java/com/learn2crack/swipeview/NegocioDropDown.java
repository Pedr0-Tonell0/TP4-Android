package com.learn2crack.swipeview;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class NegocioDropDown extends AsyncTask<String,  Void, String> {

    private static String result2;
    private static ArrayList<String> list = new ArrayList<String>();
    private Spinner lvCategory;
    private Context context;
    ArrayAdapter<String> comboAdapter;

    public NegocioDropDown(Spinner lv, Context ct)
    {
        lvCategory = lv;
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
            ResultSet rs = st.executeQuery("select descripcion from categoria ");
            while(rs.next()) {
                String item = rs.getString("descripcion");
                list.add(item);
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
        comboAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list);
        lvCategory.setAdapter(comboAdapter);
    }


}
