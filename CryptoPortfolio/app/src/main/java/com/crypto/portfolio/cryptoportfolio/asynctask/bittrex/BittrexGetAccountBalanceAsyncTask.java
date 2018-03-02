package com.crypto.portfolio.cryptoportfolio.asynctask.bittrex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.adapter.bittrex.AccountBalanceRecyclerViewAdapter;
import com.crypto.portfolio.cryptoportfolio.apiclient.BittrexClient;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.account.AccountBalanceResponse;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.market.MarketSummaryDTO;
import com.crypto.portfolio.cryptoportfolio.fragmentstate.BittrexState;

import java.util.Map;

public class BittrexGetAccountBalanceAsyncTask extends AsyncTask<Void, Void, AccountBalanceResponse> {

    private View view;
    private BittrexClient bittrexClient;
    private BittrexState bittrexState;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Context context;
    private Boolean showHoldings;

    public BittrexGetAccountBalanceAsyncTask(Context context, View view, BittrexClient bittrexClient, BittrexState bittrexState, RecyclerView mRecyclerView, RecyclerView.Adapter mAdapter, Boolean showHoldings) {
        this.view = view;
        this.bittrexClient = bittrexClient;
        this.bittrexState = bittrexState;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;
        this.context = context;
        this.showHoldings = showHoldings;
    }

    @Override
    protected AccountBalanceResponse doInBackground(Void... voids) {
        AccountBalanceResponse accountBalanceResponse =  bittrexClient
                .getAccountBalance(bittrexState.getBittrexKey(), bittrexState.getBittrexSecret());

        if (accountBalanceResponse.getSuccess()) {
            Map<String, MarketSummaryDTO> marketSummaryDTOMap = bittrexClient.getMarketSummary();
            bittrexState.setMarketSummaryMap(marketSummaryDTOMap);
            accountBalanceResponse.setResult(bittrexState.filterAndSetPrice(accountBalanceResponse.getResult()));
            bittrexState.setGetBalanceDTO(accountBalanceResponse.getResult());
        }

        return accountBalanceResponse;
    }

    private void showNoInternetConnectionAlertBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("No Internet Connection")
                .setMessage("It looks like your internet connection is off. Please turn it on and try again.")
                .setIcon(R.drawable.ic_no_internet_connection)
                .setCancelable(true)
                .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        new BittrexGetAccountBalanceAsyncTask(context, view, bittrexClient, bittrexState, mRecyclerView, mAdapter, showHoldings).execute();
                    }
                });
        builder.create().show();
    }

    private void showInvalidKeyOrSecretAlertBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle("Invalid API key or secret")
                .setMessage("Please use correct api key and secret.\nGo to  Setting > Manage Bittrex Credentials to change bittrex api key or secret.")
                .setIcon(R.drawable.ic_invalid_key)
                .setCancelable(true)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.create().show();
    }

    @Override
    protected void onPostExecute(AccountBalanceResponse accountBalanceResponse) {
        super.onPostExecute(accountBalanceResponse);

        if (accountBalanceResponse.getSuccess()) {
            String totalBTC = String.format("%.8f", bittrexState.getTotalBTC()) + " BTC";
            String usdValue = String.format("%.2f", bittrexState.getMarketSummaryMap().get("USDT-BTC").getLast() * bittrexState.getTotalBTC()) + " USD";
            ((TextView)view.findViewById(R.id.usdValue)).setText(usdValue);
            ((TextView)view.findViewById(R.id.btcValue)).setText(totalBTC);
            ((SwipeRefreshLayout)view.findViewById(R.id.bittrexRefresh)).setRefreshing(false);
            mAdapter = new AccountBalanceRecyclerViewAdapter(bittrexState.getGetBalanceDTO());
            mRecyclerView.setAdapter(mAdapter);
            view.findViewById(R.id.btcCard).setVisibility(View.VISIBLE);
            if (showHoldings) {
                view.findViewById(R.id.holdingsCard).setVisibility(View.VISIBLE);
            } else {
                view.findViewById(R.id.holdingsCard).setVisibility(View.GONE);
            }
        } else  {
            ((SwipeRefreshLayout)view.findViewById(R.id.bittrexRefresh)).setRefreshing(false);
            if (accountBalanceResponse.getApiError().getDiagnosticMessage().equals("NO_INTERNET_CONNECTION")) {
                showNoInternetConnectionAlertBox();
            } else {
                showInvalidKeyOrSecretAlertBox();
            }
        }
    }
}