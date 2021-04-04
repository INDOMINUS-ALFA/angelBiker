package com.example.angelbiker.ui.motos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelbiker.R;
import com.example.angelbiker.ui.utilitys.recycler.adapters.MotoRecyclerviewAdapter;
import com.example.angelbiker.ui.utilitys.recycler.viewHolder.MotoSViewHolder;
import com.example.angelbiker.ui.viewModels.MotosViewModel;

public class ListaMotosActivity extends AppCompatActivity {
    private MotosViewModel myMotoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_lista_motos);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewmoto_id);
        final MotoSViewHolder.MotoListAdapter adapter = new MotoSViewHolder.MotoListAdapter(new MotoSViewHolder.MotoListAdapter.WordDiff());

        recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            myMotoViewModel = new ViewModelProvider(this).get(MotosViewModel.class);
                String marca = getIntent().getStringExtra("marca");
            myMotoViewModel.getAllMotos(marca).observe(this, motos -> {
                adapter.submitList(motos);



                RecyclerView my_rw = (RecyclerView) findViewById(R.id.recyclerviewmoto_id);
                MotoRecyclerviewAdapter my_adapter = new MotoRecyclerviewAdapter(this,motos);

                my_rw.setLayoutManager(new GridLayoutManager(this,3));
                my_rw.setAdapter(my_adapter);
                
        });



    }


}