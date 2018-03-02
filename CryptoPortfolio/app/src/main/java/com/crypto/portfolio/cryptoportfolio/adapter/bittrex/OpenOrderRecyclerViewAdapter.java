package com.crypto.portfolio.cryptoportfolio.adapter.bittrex;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crypto.portfolio.cryptoportfolio.R;
import com.crypto.portfolio.cryptoportfolio.dto.response.bittrex.openorder.OpenOrderDTO;

import java.util.List;

public class OpenOrderRecyclerViewAdapter extends RecyclerView.Adapter<OpenOrderViewHolder> {

    private List<OpenOrderDTO> openOrderList;

    public OpenOrderRecyclerViewAdapter(List<OpenOrderDTO> openOrderList) {
        this.openOrderList = openOrderList;
    }

    @Override
    public OpenOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.openorder_linearlayout, parent, false);
        return new OpenOrderViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(OpenOrderViewHolder holder, int position) {
        OpenOrderDTO openOrder = openOrderList.get(position);
        holder.exchangeName.setText(openOrder.getExchange());
        holder.orderType.setText(openOrder.getOrderType());
        // just for fun
        if (openOrder.getOrderType().equalsIgnoreCase("LIMIT_SELL")) {
            holder.orderType.setTextColor(Color.parseColor("#880E4F"));
        } else {
            holder.orderType.setTextColor(Color.parseColor("#00695C"));
        }
        holder.quantityRemaining.setText(openOrder.getQuantityRemaining() + "  Coins");
        holder.limitPrice.setText(openOrder.getLimit());
    }

    @Override
    public int getItemCount() {
        return openOrderList.size();
    }
}

class OpenOrderViewHolder extends RecyclerView.ViewHolder {

    public TextView exchangeName;
    public TextView orderType;
    public TextView quantityRemaining;
    public TextView limitPrice;

    public OpenOrderViewHolder(View itemView, int index) {
        super(itemView);
        exchangeName = itemView.findViewById(R.id.exchangeName);
        orderType = itemView.findViewById(R.id.orderType);
        quantityRemaining = itemView.findViewById(R.id.quantityRemaining);
        limitPrice = itemView.findViewById(R.id.limitPrice);

    }
}