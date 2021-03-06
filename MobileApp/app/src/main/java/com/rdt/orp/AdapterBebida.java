package com.rdt.orp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBebida extends RecyclerView.Adapter<AdapterBebida.ViewHolder>{

    private ArrayList<String> bebida;
    private ArrayList<Float> bebida_preco;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    private AdapterBebida.OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(AdapterBebida.OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row,parent, false);
        return new ViewHolder(view, mListener);


    }

    public void onBindViewHolder(@NonNull AdapterBebida.ViewHolder holder, int position){
        holder.precoPrato.setText("R$ " + bebida_preco.get(position));
        holder.nomePrato.setText(bebida.get(position));
    }

    public int getItemCount() {
        return bebida.size();
    }

    AdapterBebida(ArrayList<String> pratos,ArrayList<Float> precos, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.bebida_preco = precos;
        this.bebida = pratos;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomePrato;
        TextView precoPrato;
        private RecyclerViewClickInterface recyclerViewClickInterface;

        ViewHolder(@NonNull View itemView, final AdapterBebida.OnItemClickListener listener) {
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
