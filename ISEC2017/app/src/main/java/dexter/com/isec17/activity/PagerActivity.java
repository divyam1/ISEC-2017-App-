package dexter.com.isec17.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import dexter.com.isec17.R;
import dexter.com.isec17.app.Config;
import dexter.com.isec17.fragment.ScheduleFragment;
import dexter.com.isec17.utils.NotificationUtils;
import dexter.com.isec17.utils.ZoomOutPageTransformer;

/**
 * Created by dexter on 20/1/17.
 */
public class PagerActivity extends AppCompatActivity {

    ViewPager pager;
    Toolbar toolbar;
    TextView titleToolbar;
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        pager = (ViewPager) findViewById(R.id.viewPager);
        titleToolbar = (TextView) findViewById(R.id.toolbar_title);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

//        mContainer.setViewPager(pager);
        PagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setPageTransformer(true, new ZoomOutPageTransformer(true));
        pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
//        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setOffscreenPageLimit(3);
        //A little space between pages
        pager.setPageMargin((int) getResources().getDimension(R.dimen.dimen_20));
        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    titleToolbar.setText("05th Feb");
                } else if (position == 1) {
                    titleToolbar.setText("06th Feb");
                } else {
                    titleToolbar.setText("07th Feb");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    //Nothing special about this adapter, just throwing up colored views for demo
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {


            Fragment myFragment = new ScheduleFragment();
            Bundle data = new Bundle();
            switch (index) {
                case 0:
                    data.putInt("Day", 1);
                    break;
                case 1:
                    data.putInt("Day", 2);
                    break;
                case 2:
                    data.putInt("Day", 3);
                    break;
            }
            myFragment.setArguments(data);

            return myFragment;
        }


        @Override
        public int getCount() {
            // get item count - equal to number of tabs
            return 3;
        }

    }

}