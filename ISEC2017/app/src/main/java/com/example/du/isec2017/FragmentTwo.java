package com.example.du.isec2017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by DU on 1/17/2017.
 */

public class FragmentTwo extends Fragment
{
    private ListView listView;
    ArrayList<String> allContactNames = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_two, null);
        getAllWidgets(rootView);
        setAdapter();
        return rootView;
    }

    private void getAllWidgets(View view) {
       
    }

    private void setAdapter()
    {

    }
}