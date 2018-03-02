package com.crypto.portfolio.cryptoportfolio.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.crypto.portfolio.cryptoportfolio.fragment.BinanceFragment;
import com.crypto.portfolio.cryptoportfolio.fragment.BittrexFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    String[] tabs = {"Bittrex"};

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: return new BittrexFragment();
            case 1: return new BinanceFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
