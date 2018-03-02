package com.crypto.portfolio.cryptoportfolio.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.apiclient.BinanceClient;
import com.crypto.portfolio.cryptoportfolio.asynctask.binance.BinanceGetAccountBalanceAsyncTask;
import com.crypto.portfolio.cryptoportfolio.fragmentstate.BinanceState;
import com.crypto.portfolio.cryptoportfolio.utils.SharedPreferencesUtils;

public class BinanceFragment extends Fragment {

    BinanceState binanceState = new BinanceState();
    BinanceClient binanceClient = new BinanceClient();

    SwipeRefreshLayout swipeRefreshLayout;

    /**
     * render add binance account layout
     * @param inflater
     * @param container
     * @return
     */
    private View inflateAddBinanceAccountFragment(LayoutInflater inflater, ViewGroup container) {

        View view =  inflater.inflate(R.layout.fragment_binance, container, false);

        final FloatingActionButton fab = view.findViewById(R.id.addBinanceKey);

        final TextView binanceBackgroundText = view.findViewById(R.id.binanceBackgroundText);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alBuilder = new AlertDialog.Builder(getContext());
                final View mView = getLayoutInflater().inflate(R.layout.dialog_add_binance_key, null);
                alBuilder.setView(mView);
                final AlertDialog dialog = alBuilder.create();
                mView.findViewById(R.id.add_binance_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText binance_key = mView.findViewById(R.id.binance_key);
                        EditText binance_secret = mView.findViewById(R.id.binance_secret);
                        binanceState.setBinanceKey(binance_key.getText().toString());
                        binanceState.setBinanceSecret(binance_secret.getText().toString());
                        SharedPreferencesUtils.setBinancePreference(binanceState.getBinanceKey() , binanceState.getBinanceSecret());
                        Toast.makeText(getContext(), "Binance key added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        fab.setVisibility(View.GONE);
                        binanceBackgroundText.setText("account exists.. hitting binance api");
                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    private void onRefesh(View view) {
        swipeRefreshLayout.setRefreshing(true);
        new BinanceGetAccountBalanceAsyncTask(view, binanceClient, binanceState).execute();
    }
    /**
     * render binance account balance layout
     * @param inflater
     * @param container
     * @return
     */
    private View inflateBinanceAccountBalanceFragment(LayoutInflater inflater, ViewGroup container) {

        final View view = inflater.inflate(R.layout.binance_account_info_layout, container, false);

        swipeRefreshLayout = view.findViewById(R.id.binanceRefresh);

        onRefesh(view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefesh(view);
            }
        });
        return view;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        binanceState.setBinanceKey(SharedPreferencesUtils.get(SharedPreferencesUtils.BINANCE_KEY_NAME, getContext()));
//        binanceState.setBinanceSecret(SharedPreferencesUtils.get(SharedPreferencesUtils.BINANCE_SECRET_NAME, getContext()));

        if (binanceState.getBinanceKey() != null && binanceState.getBinanceSecret() != null) {
            return inflateBinanceAccountBalanceFragment(inflater, container);
        } else {
            return inflateAddBinanceAccountFragment(inflater, container);
        }
    }
}
