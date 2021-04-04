package com.example.angelbiker.ui.utilitys.recycler.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.Moto;


public class HistoriaAdapter extends RecyclerView.Adapter<HistoriaAdapter.ViewHolderHistoria>{

    Moto mimoto;

    public HistoriaAdapter(Moto mimoto) {
        this.mimoto = mimoto;
    }

    @NonNull
    @Override
    public ViewHolderHistoria onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_historia, null, false);
        return new ViewHolderHistoria(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHistoria holder, int position) {
        holder.historia.setText(mimoto.getHistoria());


    }



    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolderHistoria extends RecyclerView.ViewHolder {
        TextView historia ;



        public ViewHolderHistoria(@NonNull View itemView) {
            super(itemView);

            historia = (TextView) itemView.findViewById(R.id.historia_moto);

        }
    }
}
