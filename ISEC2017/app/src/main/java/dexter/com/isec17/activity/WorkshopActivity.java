package dexter.com.isec17.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import dexter.com.isec17.R;
import dexter.com.isec17.adapter.WorkshopAdapter;
import dexter.com.isec17.app.Config;
import dexter.com.isec17.model.WorkshopDetail;
import dexter.com.isec17.utils.NotificationUtils;

public class WorkshopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String title[], presenters[], affiliations[], startTime[], endTime[], area[], date[];
    private ArrayList<WorkshopDetail> workshopDetailArrayList;
    private WorkshopAdapter workshopAdapter;
    private String activityDate;
    private String type;
    private TextView backDropTitle;
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backDropTitle = (TextView) findViewById(R.id.tvBackdroptitle);

        workshopDetailArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        workshopAdapter = new WorkshopAdapter(this, workshopDetailArrayList);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);


                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");
                    LayoutInflater inflater = getLayoutInflater();

                    View customToastroot = inflater.inflate(R.layout.custom_toast, null);
                    TextView toastText = (TextView) customToastroot.findViewById(R.id.tvToast);
                    toastText.setText(message);
                    Toast customtoast = new Toast(context);

                    customtoast.setView(customToastroot);
                    customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    customtoast.setDuration(Toast.LENGTH_LONG);
                    customtoast.show();
                }
            }
        };
        type = getIntent().getStringExtra("type");
        activityDate = "";
        if (type == null) {
            Toast.makeText(this, "Try Again Later !! ", Toast.LENGTH_LONG).show();
            finish();
        } else if (type.equalsIgnoreCase("workshop")) {
            activityDate = getResources().getString(R.string.workshop_date);
            title = getResources().getStringArray(R.array.workshop_title);
            presenters = getResources().getStringArray(R.array.workshop_presenter);
            affiliations = getResources().getStringArray(R.array.workshop_affiliation);
            area = getResources().getStringArray(R.array.workshop_area);
            startTime = getResources().getStringArray(R.array.workshop_start_time);
            endTime = getResources().getStringArray(R.array.workshop_end_time);
            date = getResources().getStringArray(R.array.workshop_date);
        } else {
            activityDate = getResources().getString(R.string.tutorial_date);
            title = getResources().getStringArray(R.array.tutorial_title);
            presenters = getResources().getStringArray(R.array.tutorial_presenter);
            affiliations = getResources().getStringArray(R.array.tutorial_affiliation);
            area = getResources().getStringArray(R.array.tutorial_area);
            startTime = getResources().getStringArray(R.array.tutorial_start_time);
            endTime = getResources().getStringArray(R.array.tutorial_end_time);
            date = getResources().getStringArray(R.array.tutorial_date);
        }
        type = type.toString().toUpperCase();
        backDropTitle.setText(type + "\n" + activityDate);
        initCollapsingToolbar();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        for (int i = 0; i < title.length; i++) {
            WorkshopDetail workshopDetail = new WorkshopDetail();
            workshopDetail.setTitle(title[i]);
            workshopDetail.setAffiliations(affiliations[i]);
            workshopDetail.setArea(area[i]);
            workshopDetail.setDate(date[i]);
            workshopDetail.setStartTime(startTime[i]);
            workshopDetail.setEndTime(endTime[i]);
            workshopDetail.setPresenters(presenters[i]);
            workshopDetailArrayList.add(workshopDetail);
        }


        LinearLayoutManager layoutManagerGold = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerGold);

        recyclerView.setAdapter(workshopAdapter);
        recyclerView.addOnItemTouchListener(new WorkshopAdapter.RecyclerTouchListener(this, recyclerView, new WorkshopAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), WorkshopDetailActivity.class);
                intent.putExtra("DATA", workshopDetailArrayList.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        workshopAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(type + "\n" + activityDate);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
