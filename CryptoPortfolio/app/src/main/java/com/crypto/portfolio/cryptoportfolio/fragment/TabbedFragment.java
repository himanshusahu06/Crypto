package com.crypto.portfolio.cryptoportfolio.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.adapter.ViewPageAdapter;

public class TabbedFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tabbed, container, false);
        ViewPageAdapter pageAdapter = new ViewPageAdapter(getFragmentManager());
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        tabLayout.setElevation(5.0f);
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
