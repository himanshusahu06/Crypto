package com.crypto.portfolio.cryptoportfolio.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.apiclient.BittrexClient;
import com.crypto.portfolio.cryptoportfolio.asynctask.bittrex.BittrexGetAccountBalanceAsyncTask;
import com.crypto.portfolio.cryptoportfolio.asynctask.bittrex.BittrexGetOpenOrderAsyncTask;
import com.crypto.portfolio.cryptoportfolio.fragmentstate.BittrexState;
import com.crypto.portfolio.cryptoportfolio.utils.PreferenceUtils;


public class BittrexFragment extends Fragment {

    // bittrex data state
    BittrexState bittrexState = new BittrexState();

    // bittrex api client
    BittrexClient bittrexClient = new BittrexClient();

    // shared preference change listener
    SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;

    Boolean showHoldings;
    Boolean showMarket;
    Boolean showOpenOrders;


    RecyclerView mGetAccountBalanceRecyclerView;
    RecyclerView.LayoutManager mGetAccountBalanceLayoutManager;
    RecyclerView.Adapter mGetAccountBalanceAdapter;

    RecyclerView mGetOpenOrderRecyclerView;
    RecyclerView.LayoutManager mGetOpenOrderLayoutManager;
    RecyclerView.Adapter mGetOpenOrderAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    /**
     * render add bittrex account layout
     * @param inflater
     * @param container
     * @return
     */
    private View inflateAddBittrexAccountFragment(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.fragment_bittrex, container, false);

        final FloatingActionButton fab = view.findViewById(R.id.addBittrexKey);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alBuilder = new AlertDialog.Builder(getContext());
                final View mView = getLayoutInflater().inflate(R.layout.dialog_add_bittrex_key, null);
                alBuilder.setView(mView);
                final AlertDialog dialog = alBuilder.create();
                mView.findViewById(R.id.add_bittrex_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText bittrex_key = mView.findViewById(R.id.bittrex_key);
                        EditText bittrex_secret = mView.findViewById(R.id.bittrex_secret);
                        bittrexState.setBittrexKey(bittrex_key.getText().toString());
                        bittrexState.setBittrexSecret(bittrex_secret.getText().toString());
                        PreferenceUtils.setString(getString(R.string.bittrex_key), bittrexState.getBittrexKey(), getContext());
                        PreferenceUtils.setString(getString(R.string.bittrex_secret), bittrexState.getBittrexSecret(), getContext());
                        dialog.dismiss();
                        fab.setVisibility(View.GONE);
                        Fragment currentFragment = getFragmentManager().findFragmentById(getId());
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.detach(currentFragment);
                        fragmentTransaction.attach(currentFragment);
                        fragmentTransaction.commit();

                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    /**
     * swipe refresh layout on refresh action
     * @param view
     */
    private void onRefreshTab(View view){
        swipeRefreshLayout.setRefreshing(true);
        callGetAccountBalance(view);
        toggleOpenOrderCardViewVisibility(view);
    }

    /**
     * render bittrex account balance layout
     * @param inflater
     * @param container
     * @return
     */
    private View inflateBittrexAccountBalanceFragment(LayoutInflater inflater, ViewGroup container) {

        final View view = inflater.inflate(R.layout.bittrex_account_info_layout, container, false);

        registerOnSharedPreferenceChangeListener(view);

        mGetAccountBalanceRecyclerView = view.findViewById(R.id.bittrexGetAccountBalanceRecyclerView);
        mGetAccountBalanceLayoutManager = new LinearLayoutManager(getActivity());
        mGetAccountBalanceRecyclerView.setLayoutManager(mGetAccountBalanceLayoutManager);

        swipeRefreshLayout = view.findViewById(R.id.bittrexRefresh);
        mGetOpenOrderRecyclerView = view.findViewById(R.id.bittrexGetOpenOrderRecyclerView);
        mGetOpenOrderLayoutManager = new LinearLayoutManager(getActivity());
        mGetOpenOrderRecyclerView.setLayoutManager(mGetOpenOrderLayoutManager);

        onRefreshTab(view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshTab(view);
            }
        });

        return view;
    }

    /**
     * unlisten shared preference change on destroy activity
     */
    private void unregisterOnSharedPreferenceChangeListener() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /**
     * show hide open order card view
     * @param view
     */
    private void toggleOpenOrderCardViewVisibility(final View view) {
        if (showOpenOrders) {
            new BittrexGetOpenOrderAsyncTask(view, bittrexClient, bittrexState, mGetOpenOrderRecyclerView, mGetOpenOrderAdapter).execute();
        } else {
            view.findViewById(R.id.openOrderCard).setVisibility(View.GONE);
        }
    }

    /**
     * show or hide account balance card view
     * @param view
     */
    private void toggleAccountBalanceCardVisibility(View view) {
        if (showHoldings) {
            view.findViewById(R.id.holdingsCard).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.holdingsCard).setVisibility(View.GONE);
        }
    }

    /**
     * hit get account balance API on page refresh only
     * @param view
     */
    private void callGetAccountBalance(final View view) {
        new BittrexGetAccountBalanceAsyncTask(getContext(), view, bittrexClient, bittrexState, mGetAccountBalanceRecyclerView, mGetAccountBalanceAdapter, showHoldings).execute();
    }

    /**
     * shared preference change listener
     * @param view
     */
    private void registerOnSharedPreferenceChangeListener(final View view) {

        final String showAccountBalanceKey = getString(R.string.bittrex_show_account_balance);
        final String showMarketKey = getString(R.string.bittrex_show_market);
        final String showOpenOrderKey = getString(R.string.bittrex_show_open_order);

        Context context = getContext();

        showHoldings = PreferenceUtils.getBoolean(showAccountBalanceKey, context);
        showMarket = PreferenceUtils.getBoolean(showMarketKey, context);
        showOpenOrders = PreferenceUtils.getBoolean(showOpenOrderKey, context);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener()
        {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
            {
                if (key.equals(showAccountBalanceKey)) {
                    showHoldings = !showHoldings;
                    toggleAccountBalanceCardVisibility(view);
                }
                if (key.equals(showOpenOrderKey)) {
                    showOpenOrders = !showOpenOrders;
                    toggleOpenOrderCardViewVisibility(view);
                }
                if (key.equals(getString(R.string.bittrex_key))) {
                    bittrexState.setBittrexKey(PreferenceUtils.getString(key, getContext()));
                    onRefreshTab(view);
                }
                if (key.equals(getString(R.string.bittrex_secret))) {
                    bittrexState.setBittrexSecret(PreferenceUtils.getString(key, getContext()));
                    onRefreshTab(view);
                }
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /**
     * on destroy activity lifecycle
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterOnSharedPreferenceChangeListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bittrexState.setBittrexKey(PreferenceUtils.getString(getString(R.string.bittrex_key), getContext()));
        bittrexState.setBittrexSecret(PreferenceUtils.getString(getString(R.string.bittrex_secret), getContext()));

        if (bittrexState.getBittrexKey() != null && bittrexState.getBittrexSecret() != null) {
            return inflateBittrexAccountBalanceFragment(inflater, container);
        } else {
            return inflateAddBittrexAccountFragment(inflater,container);
        }
    }
}
