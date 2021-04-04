package com.example.angelbiker.domain.repositorys;

import android.app.Application;


import androidx.lifecycle.LiveData;

import com.example.angelbiker.domain.DB.MotosDB;
import com.example.angelbiker.domain.DB.modelos.motos.Moto;
import com.example.angelbiker.domain.DB.modelos.motos.MotoDao;
import com.example.angelbiker.domain.DB.modelos.motos.MotoMinimal;

import java.util.List;

public class MotosRepository {

    private MotoDao mMotoDao;
    private LiveData<List<Moto>> mAllMotos;


    /*Note that in order to unit test the WordRepository, you have to remove the Application
    dependency. This adds complexity and much more code, and this sample is not about testing.
    See the BasicSample in the android-architecture-components repository at
     https://github.com/googlesamples*/

    public MotosRepository(Application application) {
        MotosDB db = MotosDB.getDatabase(application);
        mMotoDao = db.mainDao();

        //mAllMotos = mMotoDao.getAlphabetizedMotos(smarca);
    }
    /* Room executes all queries on a separate thread.
       Observed LiveData will notify the observer when the data has changed.*/
    public LiveData<List<Moto>> getAllMotos() {
        return mAllMotos;
    }

    public LiveData<List<MotoMinimal>> getMotosPorMarca(String marca) {
        return mMotoDao.getAlphabetizedMotos(marca);
    }
    public Moto motoSelecionada(String nombre){
        return mMotoDao.motoSelecionada(nombre);
    }



    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Moto moto) {
        MotosDB.databaseWriteExecutor.execute(() -> {
            mMotoDao.insert(moto);
        });
    }



}
