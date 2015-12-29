package com.example.krishnakumar.rss_jsoup;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.krishnakumar.rss_jsoup.Fragments.LatestJobs;
import com.example.krishnakumar.rss_jsoup.model.NaukriData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public ListView listView;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);
        tabs.setTextColor(Color.parseColor("#ffffff"));

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);



    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "Latest\nJobs", "Banking\nJobs", "Railways\nJobs", "UPSC\nJobs",
                "Teaching\nJobs", "Indian Army\nJobs"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return LatestJobs.newInstance("https://www.naukrinama.com/feed/");
                case 1:
                    return LatestJobs.newInstance("https://www.naukrinama.com/government-jobs/banking-jobs/feed/");
                case 2:
                    return LatestJobs.newInstance("https://www.naukrinama.com/government-jobs/railways-jobs/feed/");
                case 3:
                    return LatestJobs.newInstance("https://www.naukrinama.com/government-jobs/upsc-jobs/feed/");
                case 4:
                    return LatestJobs.newInstance("https://www.naukrinama.com/government-jobs/teaching-jobs/feed/");
                default:
                    return LatestJobs.newInstance("https://www.naukrinama.com/government-jobs/indian-army-jobs/");
            }

        }

    }









//end of main class
}
