package com.example.angelbiker.ui.utilitys.recycler.viewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.MotoMinimal;

import java.util.List;

public class MotoSViewHolder extends RecyclerView.ViewHolder{

    TextView nombre_moto;
    ImageView motos_img;
    CardView cardView;
    private static Context motoContext;
    private static List<MotoMinimal> motoData;
    public MotoSViewHolder(View itemView) {
        super(itemView);
        nombre_moto = itemView.findViewById(R.id.nombre_moto_id);
        motos_img = itemView.findViewById(R.id.motos_img_id);
        cardView = (CardView) itemView.findViewById(R.id.cardview_id);



    }



    public static class MotoListAdapter extends ListAdapter<MotoMinimal, MotoSViewHolder> {

            public MotoListAdapter(@NonNull DiffUtil.ItemCallback<MotoMinimal> diffCallback) {
                super(diffCallback);

            }

            @Override
            public MotoSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view;
                LayoutInflater mInflater = LayoutInflater.from(motoContext);
                view= mInflater.inflate(R.layout.cardview_item_marcas, parent, false);
                return new MotoSViewHolder(view);
            }


            @Override
            public void onBindViewHolder(@NonNull MotoSViewHolder holder, int position) {
                holder.nombre_moto.setText(motoData.get(position).getNombre());
                holder.motos_img.setImageResource(motoData.get(position).getMiniatura());
                //set click listener
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            public static class WordDiff extends DiffUtil.ItemCallback<MotoMinimal> {

                @Override
                public boolean areItemsTheSame(@NonNull MotoMinimal oldItem, @NonNull MotoMinimal newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull MotoMinimal oldItem, @NonNull MotoMinimal newItem) {
                    return oldItem.getNombre().equals(newItem.getNombre());
                }
            }
        }
}









