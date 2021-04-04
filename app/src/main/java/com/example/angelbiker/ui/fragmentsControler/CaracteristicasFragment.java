package com.example.angelbiker.ui.fragmentsControler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.Moto;
import com.example.angelbiker.ui.utilitys.recycler.adapters.CaracteristicasAdapter;


public class CaracteristicasFragment extends Fragment {
    Moto mimoto;
    RecyclerView myrecycler;

    public CaracteristicasFragment(Moto mimoto) {
        // Required empty public constructor
        this.mimoto = mimoto;


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caracteristicas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myrecycler = view.findViewById(R.id.recycler_moto_caracteristicas);
        CaracteristicasAdapter adapat = new CaracteristicasAdapter(mimoto);
        myrecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        myrecycler.setAdapter(adapat);
    }
}