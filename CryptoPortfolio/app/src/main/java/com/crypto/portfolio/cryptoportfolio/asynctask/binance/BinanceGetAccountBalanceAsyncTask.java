package com.crypto.portfolio.cryptoportfolio.asynctask.binance;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.apiclient.BinanceClient;
import com.crypto.portfolio.cryptoportfolio.dto.response.binance.GetAccountBalanceResponse;
import com.crypto.portfolio.cryptoportfolio.fragmentstate.BinanceState;

public class BinanceGetAccountBalanceAsyncTask extends AsyncTask<Void, Void, GetAccountBalanceResponse> {

    private View view;
    private BinanceClient binanceClient;
    private BinanceState binanceState;

    public BinanceGetAccountBalanceAsyncTask(View view, BinanceClient binanceClient, BinanceState binanceState) {
        this.binanceClient = binanceClient;
        this.binanceState = binanceState;
        this.view = view;
    }

    @Override
    protected GetAccountBalanceResponse doInBackground(Void... voids) {
        binanceClient.getMarket();
        return binanceClient.getAccountBalance(binanceState.getBinanceKey(), binanceState.getBinanceSecret());
    }

    @Override
    protected void onPostExecute(GetAccountBalanceResponse getAccountBalanceResponse) {
        super.onPostExecute(getAccountBalanceResponse);
        binanceState.setBalanceDTOList(getAccountBalanceResponse.getBalances());
        ((SwipeRefreshLayout)view.findViewById(R.id.binanceRefresh)).setRefreshing(false);
        final TextView binanceBackgroundText = view.findViewById(R.id.binanceBackgroundText);
        binanceBackgroundText.setText(getAccountBalanceResponse.toString());
    }
}
