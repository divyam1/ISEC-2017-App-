package dexter.com.isec17.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import dexter.com.isec17.activity.AboutLnmiitActivity;
import dexter.com.isec17.activity.ContactUsActivity;
import dexter.com.isec17.activity.OrganisationActivity;
import dexter.com.isec17.activity.PagerActivity;
import dexter.com.isec17.activity.SponsorsActivity;
import dexter.com.isec17.activity.WorkshopActivity;
import dexter.com.isec17.adapter.ItemAdapter;
import dexter.com.isec17.R;
import dexter.com.isec17.model.SpeakerItem;

/**
 * Created by dexter on 16/1/17.
 */
public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private String imageLinks[];
    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new ItemAdapter(getActivity());

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.slider_1,
                R.layout.slider_1,
                R.layout.slider_1,
                R.layout.slider_1,
                R.layout.slider_1,
                R.layout.slider_1,
                R.layout.slider_1};

        imageLinks = new String[]{"http://isec2017.in/img/header.jpg",
                "http://isec2017.in/img/jaipur1.jpg",
                "http://isec2017.in/img/lnmiit1.jpg",
                "http://isec2017.in/img/jaipur2.jpg",
                "http://isec2017.in/img/lnmiit2.jpg",
                "http://isec2017.in/img/jaipur3.jpg",
                "http://isec2017.in/img/lnmiit3.jpg"};

        // adding bottom dots
        addBottomDots(0);


        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(mLayoutManager);
        }
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new ItemAdapter.RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new ItemAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                    switch(position){
                        case 0 :
                            Intent intent1 = new Intent(getContext(),WorkshopActivity.class);
                            intent1.putExtra("type","workshop");
                            startActivity(intent1);
                            break;
                        case 1 :
                            Intent intent2 = new Intent(getContext(),WorkshopActivity.class);
                            intent2.putExtra("type","tutorial");
                            startActivity(intent2);
                            break;
                        case 2 :
                            AccomodationFragment fragment = new AccomodationFragment();
                            replaceFragment(fragment);
                            break ;
                        case 3 :
                            Intent intent = new Intent(getContext(),SponsorsActivity.class);
                            startActivity(intent);
                            break;
                        case 4 :
                            PlanFragment planfragment = new PlanFragment();
                            replaceFragment(planfragment);
                            break;
                        case 5 :
                            SpeakerFragment speakerfragment = new SpeakerFragment();
                            replaceFragment(speakerfragment);
                            break;
                        case 6 :
                            Intent organisationctivity = new Intent(getContext(), OrganisationActivity.class);
                            startActivity(organisationctivity);
                            break;
                        case 7 :
                            Intent intent3 = new Intent(getContext(),PagerActivity.class);
                            startActivity(intent3);
                            break ;
                        case 8 :
                            Intent intentAbtLnm = new Intent(getContext(),AboutLnmiitActivity.class);
                            startActivity(intentAbtLnm);
                            break;
                        case 9 :
                            Intent intentCntUs = new Intent(getContext(),ContactUsActivity.class);
                            startActivity(intentCntUs);
                            break;
                    }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view ;
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int colorsActive = getResources().getColor(R.color.dot_dark_screen1);
        int colorsInactive = getResources().getColor(R.color.dot_light_screen1);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private ImageView imageView ;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            imageView = (ImageView)view.findViewById(R.id.ivSlider);
            try{
                Glide.with(getContext()).load(imageLinks[position]).listener(requestListener).error( R.drawable.ic_photo_white_48dp ).placeholder(R.drawable.ic_photo_white_48dp).into(imageView);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // important to return false so the error placeholder can be placed
            System.out.println("Exception " + e.getMessage());
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            System.out.println("Ready");
            return false;
        }
    };

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
