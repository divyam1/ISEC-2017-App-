package dexter.com.isec17.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import dexter.com.isec17.fragment.AccomodationFragment;
import dexter.com.isec17.fragment.HomeFragment;
import dexter.com.isec17.fragment.PlanFragment;
import dexter.com.isec17.fragment.SpeakerFragment;
import dexter.com.isec17.utils.NotificationUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        int id = item.getItemId();
        if(id == R.id.workshops){
            Intent intent1 = new Intent(getApplicationContext(),WorkshopActivity.class);
            intent1.putExtra("type","workshop");
            startActivity(intent1);
        }
        if(id == R.id.accomodation){

            AccomodationFragment accomodationfragment = new AccomodationFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, accomodationfragment);
            fragmentTransaction.commit();

//            lockAppBar(false,"Home");
        }
        if(id == R.id.sponsers){
            Intent intent = new Intent(getApplicationContext(),SponsorsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.contactUs){
            Intent intentCntUs = new Intent(getApplicationContext(),ContactUsActivity.class);
            startActivity(intentCntUs);

        }
        if(id == R.id.aboutLnmiit){
            Intent intentAbtLnm = new Intent(getApplicationContext(),AboutLnmiitActivity.class);
            startActivity(intentAbtLnm);

        }
        if(id == R.id.tutorials){
            Intent intent1 = new Intent(getApplicationContext(),WorkshopActivity.class);
            intent1.putExtra("type","tutorials");
            startActivity(intent1);

        }
        if(id ==R.id.keytalks)
        {
            SpeakerFragment speakerFragment = new SpeakerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, speakerFragment);
            fragmentTransaction.commit();
        }
        if(id == R.id.organization)
        {
            Intent intentOrg = new Intent(getApplicationContext(),OrganisationActivity.class);
            startActivity(intentOrg);
        }
        if(id == R.id.schedule)
        {
            Intent intentSch = new Intent(getApplicationContext(),PagerActivity.class);
            startActivity(intentSch);
        }
        if (id == R.id.planner)
        {
            PlanFragment planfragment = new PlanFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, planfragment);
            fragmentTransaction.commit();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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