package com.crypto.portfolio.cryptoportfolio.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.crypto.portfolio.cryptoportfolio.R;

public class BittrexCredentialSettingActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addPreferencesFromResource(R.xml.bittrex_preference);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
