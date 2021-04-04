package com.example.angelbiker.ui.motos;

import android.Manifest;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.Marca;

import com.example.angelbiker.ui.home.HomeFragment;
import com.example.angelbiker.ui.location.listLocation.ListLocationActivity;
import com.example.angelbiker.ui.utilitys.recycler.adapters.MarcaRecyclerviewAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class ListaMarcasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<Marca> lstmarca;
    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_marcas);

        ActivityCompat.requestPermissions(ListaMarcasActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);



        lstmarca = new ArrayList<>();
        lstmarca.add(new Marca("Aprilia", R.drawable.aprilia));
        lstmarca.add(new Marca("Kawasaki", R.drawable.kawasaki));
        lstmarca.add(new Marca("Yamaha", R.drawable.yamaha));
        lstmarca.add(new Marca("Suzuki", R.drawable.suzuki));
        lstmarca.add(new Marca("BMW", R.drawable.bmw));
        lstmarca.add(new Marca("KTM", R.drawable.ktm));
        lstmarca.add(new Marca("Honda", R.drawable.honda));
        lstmarca.add(new Marca("Harley Davison", R.drawable.harley));
        lstmarca.add(new Marca("Triumph", R.drawable.triumph));
        lstmarca.add(new Marca("MV Augustav", R.drawable.mv_augustav));
        lstmarca.add(new Marca ("Benelli", R.drawable.benelli));
        lstmarca.add(new Marca("Ducati", R.drawable.ducati));
        lstmarca.add(new Marca("Husqvarna", R.drawable.husqvarna));
        lstmarca.add(new Marca("Peugeot", R.drawable.peugeot));
        lstmarca.add(new Marca("Piaggio", R.drawable.piaggio));
        lstmarca.add(new Marca("Royal Enfield", R.drawable.royal_enfield));




        RecyclerView my_rw = findViewById(R.id.recyclerview_id);
        MarcaRecyclerviewAdapter my_adapter = new MarcaRecyclerviewAdapter(this, lstmarca);

        my_rw.setLayoutManager(new GridLayoutManager(this,3));
        my_rw.setAdapter(my_adapter);



        }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i;
        switch(item.getItemId()) {
            case R.id.nav_home:
                i = new Intent(this, HomeFragment.class);
                startActivity(i);
                finish();
                return true;
            case R.id.Motos:
                i = new Intent(this, ListaMarcasActivity.class);
                startActivity(i);
                finish();
                return true;
            case R.id.Ubicaciones:
                i = new Intent(this, ListLocationActivity.class);
                startActivity(i);
                finish();
        }

        DrawerLayout drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}