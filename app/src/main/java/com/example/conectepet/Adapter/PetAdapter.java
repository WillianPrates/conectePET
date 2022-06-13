package com.example.conectepet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conectepet.Model.PetModel;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    Context context;

    ArrayList<PetModel> list;

    public PetAdapter(Context context, ArrayList<PetModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(com.example.conectepet.R.layout.item, parent,false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {

        PetModel petModel = list.get(position);
        holder.cardNome.setText(petModel.getNomePet());
        holder.cardNasc.setText(petModel.getDataNasc());
        holder.cardEspecie.setText(petModel.getEspecie());
        holder.cardCastrado.setText(petModel.getCastrado());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{

        TextView cardNome, cardNasc, cardEspecie, cardCastrado;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            cardNome = itemView.findViewById(com.example.conectepet.R.id.cardNome);
            cardNasc = itemView.findViewById(com.example.conectepet.R.id.cardNasc);
            cardEspecie = itemView.findViewById(com.example.conectepet.R.id.cardEspecie);
            cardCastrado = itemView.findViewById(com.example.conectepet.R.id.cardCastrado);
        }
    }
}
