package com.crypto.portfolio.cryptoportfolio.adapter.bittrex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.account.GetBalanceDTO;

import java.util.List;

public class AccountBalanceRecyclerViewAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {

    private List<GetBalanceDTO> currencyList;

    public AccountBalanceRecyclerViewAdapter(List<GetBalanceDTO> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_linearlayout, parent, false);
        return new CurrencyViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        GetBalanceDTO currency = currencyList.get(position);
        holder.currencyName.setText(currency.getCurrency());
        holder.currencyCount.setText(String.format("%.8f", currency.getBalance()));
        if (currency.getCurrentPrice() != null) {
            holder.currencyUnitPrice.setText(String.format("%.8f", currency.getCurrentPrice()));
        } else {
            holder.currencyUnitPrice.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }
}


class CurrencyViewHolder extends RecyclerView.ViewHolder {

    public TextView currencyName;
    public TextView currencyCount;
    public TextView currencyUnitPrice;
    //public TextView currenctTotalPrice;

    public CurrencyViewHolder(View itemView, int index) {
        super(itemView);
        currencyName = itemView.findViewById(R.id.currencyName);
        currencyCount = itemView.findViewById(R.id.currencyCount);
        currencyUnitPrice = itemView.findViewById(R.id.currencyUnitPrice);
        //currenctTotalPrice = itemView.findViewById(R.id.currenctTotalPrice);
    }
}