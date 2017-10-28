package dexter.com.isec17.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dexter.com.isec17.R;
import dexter.com.isec17.adapter.HotelItemAdapter;
import dexter.com.isec17.adapter.ScheduleAdpater;
import dexter.com.isec17.model.HotelDetail;
import dexter.com.isec17.model.PlannerItem;

/**
 * Created by dexter on 23/1/17.
 */
public class ScheduleFragment extends Fragment {

    String[] title, subtitle, startTime, endTime, location, type;
    String date;
    List<PlannerItem> dayScheduleList;
    private RecyclerView recyclerView;
    private ScheduleAdpater adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_plan, container, false);
        Bundle data = getArguments();
        int day = data.getInt("Day");
        System.out.println("Day : " + day);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        dayScheduleList = new ArrayList<>();
        if (day == 1) {
            title = getActivity().getResources().getStringArray(R.array.day_1_title);
            subtitle = getActivity().getResources().getStringArray(R.array.day_1_subtitle);
            startTime = getActivity().getResources().getStringArray(R.array.day_1_start_time);
            endTime = getActivity().getResources().getStringArray(R.array.day_1_end_time);
            location = getActivity().getResources().getStringArray(R.array.day_1_place);
            type = title ;
            date = "05/02/2017";
        } else if (day == 2) {
            title = getActivity().getResources().getStringArray(R.array.day_2_title);
            subtitle = getActivity().getResources().getStringArray(R.array.day_2_subtitle);
            startTime = getActivity().getResources().getStringArray(R.array.day_2_start_time);
            endTime = getActivity().getResources().getStringArray(R.array.day_2_end_time);
            location = getActivity().getResources().getStringArray(R.array.day_2_place);
            type = title ;
            date = "06/02/2017";
        } else if (day == 3) {
            title = getActivity().getResources().getStringArray(R.array.day_3_title);
            subtitle = getActivity().getResources().getStringArray(R.array.day_3_subtitle);
            startTime = getActivity().getResources().getStringArray(R.array.day_3_start_time);
            endTime = getActivity().getResources().getStringArray(R.array.day_3_end_time);
            location = getActivity().getResources().getStringArray(R.array.day_3_place);
            type = title ;
            date = "07/02/2017";
        }
        for (int i = 0; i < title.length; i++) {
            PlannerItem item = new PlannerItem();
            item.setTitle(title[i]);
            item.setSubtitle(subtitle[i]);
            item.setType(type[i]);
            item.setDate(date);
            item.setPlace(location[i]);
            item.setStartTime(startTime[i]);
            item.setEndTime(endTime[i]);
            dayScheduleList.add(item);
        }
        adapter = new ScheduleAdpater(dayScheduleList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
