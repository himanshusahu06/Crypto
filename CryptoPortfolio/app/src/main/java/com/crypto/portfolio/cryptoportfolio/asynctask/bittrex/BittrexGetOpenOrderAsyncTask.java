package com.crypto.portfolio.cryptoportfolio.asynctask.bittrex;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.adapter.bittrex.OpenOrderRecyclerViewAdapter;
import com.crypto.portfolio.cryptoportfolio.apiclient.BittrexClient;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.openorder.OpenOrderResponse;
import com.crypto.portfolio.cryptoportfolio.fragmentstate.BittrexState;

public class BittrexGetOpenOrderAsyncTask extends AsyncTask<Void, Void, OpenOrderResponse> {

    private View view;
    private BittrexClient bittrexClient;
    private BittrexState bittrexState;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public BittrexGetOpenOrderAsyncTask(View view, BittrexClient bittrexClient, BittrexState bittrexState, RecyclerView mRecyclerView, RecyclerView.Adapter mAdapter) {
        this.view = view;
        this.bittrexClient = bittrexClient;
        this.bittrexState = bittrexState;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    protected OpenOrderResponse doInBackground(Void... voids) {
        OpenOrderResponse openOrderResponse = bittrexClient
                .getOpenOrders(bittrexState.getBittrexKey(), bittrexState.getBittrexSecret());

        if (openOrderResponse.getSuccess()) {
            bittrexState.setOpenOrderDTO(openOrderResponse.getResult());
        }
        return openOrderResponse;
    }

    @Override
    protected void onPostExecute(OpenOrderResponse openOrderResponse) {
        super.onPostExecute(openOrderResponse);
        if (openOrderResponse.getSuccess()) {
            ((SwipeRefreshLayout)view.findViewById(R.id.bittrexRefresh)).setRefreshing(false);
            mAdapter = new OpenOrderRecyclerViewAdapter(bittrexState.getOpenOrderDTO());
            mRecyclerView.setAdapter(mAdapter);
            view.findViewById(R.id.openOrderCard).setVisibility(View.VISIBLE);
        }
    }
}
