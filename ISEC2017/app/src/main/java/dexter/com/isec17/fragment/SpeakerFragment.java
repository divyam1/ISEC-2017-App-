package dexter.com.isec17.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dexter.com.isec17.R;
import dexter.com.isec17.activity.SpeakerActivity;
import dexter.com.isec17.activity.WorkshopDetailActivity;
import dexter.com.isec17.adapter.SpeakerAdapter;
import dexter.com.isec17.model.SpeakerItem;

/**
 * Created by dexter on 22/1/17.
 */
public class SpeakerFragment extends Fragment {
    String name[], abstract_note[], position[], bio[], imageLink[];
    List<SpeakerItem> speakerItemsList;
    private RecyclerView recyclerView;
    private SpeakerAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accomodation, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        speakerItemsList = new ArrayList<>();
        adapter = new SpeakerAdapter(getActivity(), speakerItemsList);

        name = getActivity().getResources().getStringArray(R.array.key_note_speakers);
        abstract_note = getActivity().getResources().getStringArray(R.array.key_note_abstract);
        position = getActivity().getResources().getStringArray(R.array.key_note_occupation);
        bio = getActivity().getResources().getStringArray(R.array.key_note_bio);
        imageLink = getActivity().getResources().getStringArray(R.array.key_note_image);

        for(int i = 0; i < name.length ; i++){
            SpeakerItem item = new SpeakerItem();
            item.setAbstract_note(abstract_note[i]);
            item.setBio(bio[i]);
            item.setImgLink(imageLink[i]);
            item.setName(name[i]);
            item.setPosition(position[i]);
            speakerItemsList.add(item);
        }


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new SpeakerAdapter.RecyclerTouchListener(getActivity(), recyclerView, new SpeakerAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                    Intent intent = new Intent(getActivity(),SpeakerActivity.class);
                    intent.putExtra("DATA", speakerItemsList.get(position));
                    startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }
}

