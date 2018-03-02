package com.crypto.portfolio.cryptoportfolio.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.crypto.portfolio.cryptoportfolio.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager mViewPager;
    MPagerAdapter mPagerAdapter;
    LinearLayout dotLayout;
    ImageView[] dotImageViews;
    Button btnNext, btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (new SharedPreferenceManager(this).checkPreference()) {
            startMainActivity();
        }

        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);
        dotLayout = findViewById(R.id.dotLayout);
        mViewPager = findViewById(R.id.sliderViewPager);
        mPagerAdapter = new MPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        createDot(0);

        btnNext = findViewById(R.id.slider_btn_next);
        btnSkip = findViewById(R.id.slider_btn_skip);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                createDot(position);
                if (position == mPagerAdapter.getCount() -1 ) {
                    btnNext.setText("Start");
                    btnSkip.setVisibility(View.GONE);
                } else {
                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void createDot(int position) {
        if (dotLayout != null) {
            dotLayout.removeAllViews();
        }

        dotImageViews = new ImageView[mPagerAdapter.getCount()];

        for (int i = 0 ; i < mPagerAdapter.getCount() ; i++) {
            dotImageViews[i] = new ImageView(this);
            if (i == position) {
                dotImageViews[i].setImageDrawable(getDrawable(R.drawable.slider_dot_default));
            } else {
                dotImageViews[i].setImageDrawable(getDrawable(R.drawable.slider_dot_selected));
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(4,0,4,0);
            dotLayout.addView(dotImageViews[i], layoutParams);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.slider_btn_next:
                loadNextSlider();
                break;
            case R.id.slider_btn_skip:
                new SharedPreferenceManager(this).writePreference();
                startMainActivity();
                break;
        }
    }

    private void loadNextSlider() {
        int nextSlider =  mViewPager.getCurrentItem() + 1;
        if (nextSlider < mPagerAdapter.getCount()) {
            mViewPager.setCurrentItem(nextSlider);
        } else {
            new SharedPreferenceManager(this).writePreference();
            startMainActivity();
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}


class MPagerAdapter extends PagerAdapter {

    private int[] layouts = {R.layout.slide_first, R.layout.slide_second, R.layout.slide_third};
    private LayoutInflater layoutInflater;
    private Context context;

    public MPagerAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}

class SharedPreferenceManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPreferenceManager(Context context) {
        this.context = context;
        getSharedPreference();
    }

    private void getSharedPreference() {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.welcome_preference), Context.MODE_PRIVATE);
    }

    public void writePreference() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.welcome_preference_key), true);
        editor.commit();
    }

    public boolean checkPreference() {
        return sharedPreferences.getBoolean(context.getString(R.string.welcome_preference_key), false);
    }

    public void clearPreference() {
        sharedPreferences.edit().clear().commit();
    }
}