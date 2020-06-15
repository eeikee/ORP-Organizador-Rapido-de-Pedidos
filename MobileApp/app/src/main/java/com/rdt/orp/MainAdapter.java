package com.rdt.orp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private ArrayList<String> prato_pr;
    private ArrayList<Float> prato_pr_preco;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row,parent, false);
        return new ViewHolder(view, mListener);


    }

    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position){
        holder.precoPrato.setText("R$ " + prato_pr_preco.get(position));
        holder.nomePrato.setText(prato_pr.get(position));
    }

    public int getItemCount() {
        return prato_pr.size();
    }


    MainAdapter(ArrayList<String> pratos,ArrayList<Float> precos, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.prato_pr_preco = precos;
        this.prato_pr = pratos;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomePrato;
        TextView precoPrato;
        private RecyclerViewClickInterface recyclerViewClickInterface;

        ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            nomePrato = itemView.findViewById(R.id.nomePrato);
            precoPrato = itemView.findViewById(R.id.precoPrato);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClickListener(position);
                        }
                    }
                }
            });

        }
    }
}
