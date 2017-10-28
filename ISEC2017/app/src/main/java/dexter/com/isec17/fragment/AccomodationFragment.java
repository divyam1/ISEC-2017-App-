package dexter.com.isec17.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dexter.com.isec17.model.HotelDetail;
import dexter.com.isec17.adapter.HotelItemAdapter;
import dexter.com.isec17.R;

/**
 * Created by dexter on 19/1/17.
 */
public class AccomodationFragment extends Fragment {

    String contact[], name[], address[], rating[], price[], imageLink[], webLink[];
    private RecyclerView recyclerView;
    private HotelItemAdapter adapter ;
    ArrayList<HotelDetail> hotelDetailArrayList ;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accomodation, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        hotelDetailArrayList = new ArrayList<>();
        adapter = new HotelItemAdapter(getActivity(),hotelDetailArrayList);
        contact = getResources().getStringArray(R.array.hotel_contact);
        name = getResources().getStringArray(R.array.hotel_name);
        address = getResources().getStringArray(R.array.hotel_address);
        rating = getResources().getStringArray(R.array.hotels_rating);
        price = getResources().getStringArray(R.array.hotels_price);
        imageLink = getResources().getStringArray(R.array.hotels_image);
        webLink = getResources().getStringArray(R.array.hotels_weblink);

        for (int i = 0; i < name.length; i++) {
            HotelDetail hotelDetail = new HotelDetail(name[i], address[i], contact[i], webLink[i], imageLink[i], rating[i], price[i]);
            hotelDetailArrayList.add(hotelDetail);
        }
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
            recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new HotelItemAdapter.RecyclerTouchListener(getActivity(), recyclerView, new HotelItemAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(hotelDetailArrayList.get(position).getLink()));
                startActivity(browserIntent);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }
}
