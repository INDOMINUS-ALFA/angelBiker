package com.example.angelbiker.ui.fragmentsControler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.angelbiker.domain.DB.modelos.motos.Moto;


public class PagerControler  extends FragmentPagerAdapter {
    int numftabs;
    Moto miMoto;
    public PagerControler(@NonNull FragmentManager fm, int behavior, Moto miMoto) {
        super(fm, behavior);
        this.numftabs = behavior;
        this.miMoto = miMoto;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:

                CaracteristicasFragment caract = new CaracteristicasFragment(miMoto);
                return  caract;
            case 1:
                HistoriaFragment hist = new HistoriaFragment(miMoto);
                return  hist;
            default:
        }


        return null;
    }

    @Override
    public int getCount() {
        return numftabs;
    }
}
