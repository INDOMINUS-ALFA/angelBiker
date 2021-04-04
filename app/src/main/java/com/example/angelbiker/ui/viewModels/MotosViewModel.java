package com.example.angelbiker.ui.viewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.angelbiker.domain.DB.modelos.motos.Moto;
import com.example.angelbiker.domain.DB.modelos.motos.MotoMinimal;
import com.example.angelbiker.domain.repositorys.MotosRepository;

import java.util.List;

public class MotosViewModel extends AndroidViewModel {

    private MotosRepository mRepository;

    private final LiveData<List<Moto>> mAllMotos;
    private Moto moto;

    public MotosViewModel(Application application) {
        super(application);
        mRepository = new MotosRepository(application);
        mAllMotos = mRepository.getAllMotos();

    }

    public LiveData<List<MotoMinimal>> getAllMotos(String marca) { return mRepository.getMotosPorMarca(marca); }

   

    public void insert(Moto moto) { mRepository.insert(moto); }

    public Moto motoSelecionada(String nombre) {
        return mRepository.motoSelecionada(nombre);
    }
}