package dexter.com.isec17.activity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessaging;

import dexter.com.isec17.R;
import dexter.com.isec17.app.Config;
import dexter.com.isec17.model.PlannerItem;
import dexter.com.isec17.model.WorkshopDetail;
import dexter.com.isec17.utils.DatabaseHandler;
import dexter.com.isec17.utils.NotificationUtils;

public class WorkshopDetailActivity extends AppCompatActivity {

    private ImageView backdrop;
    private TextView title, date, time, area, presenters, affiliations;
    private Snackbar snackbar;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initCollapsingToolbar();
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
        title = (TextView) findViewById(R.id.tvTitle);
        date = (TextView) findViewById(R.id.tvDate);
        time = (TextView) findViewById(R.id.tvTime);
        area = (TextView) findViewById(R.id.tvArea);
        presenters = (TextView) findViewById(R.id.tvpresenter);
        affiliations = (TextView) findViewById(R.id.tvaffliations);

        backdrop = (ImageView) findViewById(R.id.main_backdrop);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        String link = "http://images.shiksha.com/mediadata/images/1454406835php1IJGRJ.jpeg";
        try {
            Glide.with(this).load(link).into(backdrop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final WorkshopDetail workshopDetail = (WorkshopDetail) getIntent().getSerializableExtra("DATA");
        System.out.println("Workshop Detail : " + workshopDetail);
        if (workshopDetail != null) {
            title.setText(workshopDetail.getTitle());
            date.setText(workshopDetail.getDate());
            time.setText(workshopDetail.getStartTime() + " - " + workshopDetail.getEndTime());
            area.setText(workshopDetail.getArea());
            String presenterText = workshopDetail.getPresenters().replace(" : ", "\n");
            String affiliationText = workshopDetail.getAffiliations().replace(" : ", "\n");
            presenters.setText(presenterText);
            affiliations.setText(affiliationText);
        } else {
            Toast.makeText(this, "Try Again !!", Toast.LENGTH_LONG).show();
            finish();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar = Snackbar.make(view, "Do you want to add this to your planner", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("ADD", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseHandler db = new DatabaseHandler(WorkshopDetailActivity.this);
                        PlannerItem item = new PlannerItem();
                        item.setTitle(workshopDetail.getTitle());
                        item.setStartTime(workshopDetail.getStartTime());
                        item.setPlace(workshopDetail.getArea());
                        item.setDate(workshopDetail.getDate());
                        item.setType("Workshop | Tutorial");
                        int id = (int) db.addPlan(item);
                        scheduleNotification(getNotification("10 seconds delay", id), 10000, id);
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });
    }

    private void scheduleNotification(Notification notification, int delay, int id) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, id);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content, int id) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification " + id);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
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

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing);
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
                    collapsingToolbar.setTitle("WorkShop");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
