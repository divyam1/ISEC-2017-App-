package com.example.du.isec2017;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class daysSchedule extends AppCompatActivity {

    public static daysSchedule instance;

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private TabLayout allTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_schedule);
        instance=this;

        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }

    public static daysSchedule getInstance() {
        return instance;
    }

    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupTabLayout() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree=new FragmentThree();
        allTabs.addTab(allTabs.newTab().setText("Day1"),true);
        allTabs.addTab(allTabs.newTab().setText("Day2"));
        allTabs.addTab(allTabs.newTab().setText("Day3"));
    }

    private void bindWidgetsWithAnEvent()
    {
        allTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(fragmentOne);
            case 1 :
                replaceFragment(fragmentTwo);
                break;
            case 2 :
                replaceFragment(fragmentThree);
                break;

        }
    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

}
