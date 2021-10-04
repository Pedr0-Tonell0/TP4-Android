package com.learn2crack.swipeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    public static final String TITLE = "LISTADO";
    ListView list;
    ArrayAdapter adapter;
    ArrayList<Products> listProducts;
    public static SettingsFragment newInstance() {

        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings,container,false);
        list = (ListView)v.findViewById(R.id.list);
        Connect();
        return v;
    }
    public void Connect() {
        NegocioList task = new NegocioList(list,getContext());
        task.execute();
    }
}