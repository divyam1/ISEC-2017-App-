package com.example.du.isec2017;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class FragmentOne extends Fragment {

    private GridView gridView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_one, null);
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
