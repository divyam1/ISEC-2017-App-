package dexter.com.isec17.fragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dexter.com.isec17.R;
import dexter.com.isec17.activity.NotificationPublisher;
import dexter.com.isec17.adapter.PlanAdapter;
import dexter.com.isec17.model.PlannerItem;
import dexter.com.isec17.utils.DatabaseHandler;


/**
 * Created by dexter on 21/1/17.
 */
public class PlanFragment extends Fragment {

    private RecyclerView recyclerView;
    private PlanAdapter adapter;
    private List<PlannerItem> plannerItemList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        plannerItemList = new ArrayList<>();

        adapter = new PlanAdapter(plannerItemList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        DatabaseHandler db = new DatabaseHandler(getActivity());
        plannerItemList = db.getAllPlans();

        adapter.update(plannerItemList);

        recyclerView.addOnItemTouchListener(new PlanAdapter.RecyclerTouchListener(getActivity(), recyclerView, new PlanAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                    dialogBox("Do you want to remove this from your planner ?",position);
            }
        }));
        return view;
    }

    public void dialogBox(String title , final int position ) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(title);
        alertDialogBuilder.setPositiveButton("DELETE",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DatabaseHandler db = new DatabaseHandler(getActivity());
                        PlannerItem plannerItem = plannerItemList.get(position);
                        if(plannerItem != null){
                            db.deletePlan(plannerItem);
                            int id = plannerItem.getId();
                            deleteNotification(id);
                            plannerItemList.remove(position);
                            adapter.update(plannerItemList);
                        }else{
                            Toast.makeText(getActivity(), "Try Again Later !!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void deleteNotification(int id){
        Intent intent = new Intent(getActivity(), NotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getActivity().getApplicationContext(),
                id, intent, 0);
        AlarmManager am = (AlarmManager) getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.cancel(pendingIntent);
        // Cancel the `PendingIntent` after you've canceled the alarm
        pendingIntent.cancel();
    }

}
