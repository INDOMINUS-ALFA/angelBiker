package com.example.angelbiker.ui.utilitys.recycler.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.Marca;
import com.example.angelbiker.ui.motos.ListaMotosActivity;

import java.util.List;

public class    MarcaRecyclerviewAdapter extends RecyclerView.Adapter<MarcaRecyclerviewAdapter.MyViewHolder> {
    private Context mContext;
    private List<Marca> mData;

    public MarcaRecyclerviewAdapter(Context mContext, List<Marca> mData) {
        this.mContext = mContext;
        this.mData = mData;


    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view= mInflater.inflate(R.layout.cardview_item_marcas, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_marca_titulo.setText(mData.get(position).getNombre());
        holder.imagen_marca_miniatura.setImageResource(mData.get(position).getMiniatura());
        //set click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mContext, ListaMotosActivity.class);
                String marca = (String) holder.tv_marca_titulo.getText();
                intent.putExtra("marca", marca);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_marca_titulo;
            ImageView imagen_marca_miniatura;
            CardView cardView;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_marca_titulo = (TextView) itemView.findViewById(R.id.nombreMarca);
            imagen_marca_miniatura = (ImageView) itemView.findViewById(R.id.maraca_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
