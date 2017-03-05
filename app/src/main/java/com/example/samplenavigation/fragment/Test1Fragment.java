package com.example.samplenavigation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.samplenavigation.R;
import com.example.samplenavigation.util.ClickableViewPager;
import com.viewpagerindicator.LinePageIndicator;

/**
 * Created by Rashmi on 3/4/2017.
 */

public class Test1Fragment extends Fragment {

    AppCompatTextView tvItem1, tvItem2, tvItem3, tvItem4, tvItem5, tvDisplay;
    LinearLayout layoutMain;
    AppCompatButton btnRed, btnGreen, btnBlue;
    ClickableViewPager viewPager;
    CustomPagerAdapter customPagerAdapter;
    LinePageIndicator viewPagerIndicator;

    public Test1Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test1_view, container, false);

        setView(rootView);
        return rootView;
    }

    private void setView(View view) {
        tvDisplay = (AppCompatTextView) view.findViewById(R.id.tvDisplay);
        tvItem1 = (AppCompatTextView) view.findViewById(R.id.tvItem1);
        tvItem2 = (AppCompatTextView) view.findViewById(R.id.tvItem2);
        tvItem3 = (AppCompatTextView) view.findViewById(R.id.tvItem3);
        tvItem4 = (AppCompatTextView) view.findViewById(R.id.tvItem4);
        tvItem5 = (AppCompatTextView) view.findViewById(R.id.tvItem5);

        layoutMain = (LinearLayout) view.findViewById(R.id.layoutMain);

        btnRed = (AppCompatButton) view.findViewById(R.id.btnRed);
        btnGreen = (AppCompatButton) view.findViewById(R.id.btnGreen);
        btnBlue = (AppCompatButton) view.findViewById(R.id.btnBlue);

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMain.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMain.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutMain.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        });

        tvDisplay.setText(tvItem1.getText().toString());

        tvItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText(tvItem1.getText().toString());
            }
        });

        tvItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText(tvItem2.getText().toString());
            }
        });

        tvItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText(tvItem3.getText().toString());
            }
        });

        tvItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText(tvItem4.getText().toString());
            }
        });

        tvItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText(tvItem5.getText().toString());
            }
        });

        viewPagerIndicator = (LinePageIndicator) view.findViewById(R.id.viewPagerIndicator);
        viewPagerIndicator.setStrokeWidth(10);

        viewPager = (ClickableViewPager) view.findViewById(R.id.viewpager);
        customPagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(customPagerAdapter);
        viewPagerIndicator.setViewPager(viewPager);

        viewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity().getApplicationContext(), viewPager.getAdapter().getPageTitle(viewPager.getCurrentItem())+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class CustomPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public CustomPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ViewPagerFragment.newInstance(0, "Fragment # 1");
                case 1:
                    return ViewPagerFragment.newInstance(1, "Fragment # 2");
                case 2:
                    return ViewPagerFragment.newInstance(2, "Fragment # 3");
                case 3:
                    return ViewPagerFragment.newInstance(2, "Fragment # 4");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Fragment # " + (position+1);
        }
    }
}
