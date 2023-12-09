package com.payment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.payment.models.CardModel;
import com.payment.models.ResponseData.UserData;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<UserData> cardList;

    public CardAdapter(List<UserData> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        UserData card = cardList.get(position);
        holder.bank_Name.setText(card.getBankName());
        holder.Balance.setText(card.getBalance());

        // Add click listeners or other functionality here
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView bank_Name;
        TextView Balance;
        ImageView addButton;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            bank_Name = itemView.findViewById(R.id.bankName);
            Balance = itemView.findViewById(R.id.balanceamount);

        }
    }
}

