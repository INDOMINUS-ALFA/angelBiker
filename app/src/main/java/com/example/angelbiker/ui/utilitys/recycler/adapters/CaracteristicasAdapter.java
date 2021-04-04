package com.example.angelbiker.ui.utilitys.recycler.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.Moto;


public class CaracteristicasAdapter extends RecyclerView.Adapter<CaracteristicasAdapter.ViewHolderCaracteristicas> {

    Moto mimoto;
    String[] nombres = new String[]{"año", "cilindros", "Par Motor", "Peso", "Precio", "Suspension", "Motor", "Frenos", "Especificaciones", "Categoria", "Marca", "Tipo Carnet"};

    public CaracteristicasAdapter(Moto mimoto) {
        this.mimoto = mimoto;
    }

    @NonNull
    @Override
    public ViewHolderCaracteristicas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recicler_especificaciones, null, false);
        return new ViewHolderCaracteristicas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCaracteristicas holder, int position) {
        holder.nombre.setText(nombres[position]);
        switch (position) {
            case 0:
                holder.descripcion.setText(mimoto.getAnio() + "");
                break;
            case 1:
                holder.descripcion.setText(mimoto.getCilindros() + "");
                break;
            case 2:
                holder.descripcion.setText(mimoto.getParMotor() + "Nm");
                break;
            case 3:
                holder.descripcion.setText(mimoto.getPeso() + "Kg");
                break;
            case 4:
                holder.descripcion.setText(mimoto.getPrecio() + "€");
                break;
            case 5:
                holder.descripcion.setText(mimoto.getSuspension());
                break;
            case 6:
                holder.descripcion.setText(mimoto.getMotor());
                break;
            case 7:
                holder.descripcion.setText(mimoto.getFrenos());
                break;
            case 8:
                holder.descripcion.setText(mimoto.getEspecificaciones());
                break;
            case 9:
                holder.descripcion.setText(mimoto.getCategoria());
                break;
            case 10:
                holder.descripcion.setText(mimoto.getMarca());
                break;
            case 11:
                holder.descripcion.setText(mimoto.getTipo_carnet());
                break;
        }
    }


    @Override
    public int getItemCount() {
        return nombres.length;
    }

    public class ViewHolderCaracteristicas extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView descripcion;


        public ViewHolderCaracteristicas(@NonNull View itemView) {
            super(itemView);

            nombre =  itemView.findViewById(R.id.nombre);
            descripcion =  itemView.findViewById(R.id.descripcion);
        }
    }
}
