package com.example.angelbiker.ui.motos;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.modelos.motos.Moto;
import com.example.angelbiker.ui.fragmentsControler.PagerControler;
import com.example.angelbiker.ui.utilitys.StatsValueFormatter;
import com.example.angelbiker.ui.viewModels.MotosViewModel;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MotoEspecificaActivity extends AppCompatActivity {
    private MotosViewModel myMotoViewModel;
    private RadarChart statsChart;
    Moto mimoto;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.transaction);
        mediaPlayer.start();

        setContentView(R.layout.activity_moto_especifica);


        myMotoViewModel = new ViewModelProvider(this).get(MotosViewModel.class);

        String nombre = getIntent().getStringExtra("nombre");
        myMotoViewModel.motoSelecionada(nombre);
        int miniatura = myMotoViewModel.motoSelecionada(nombre).getMiniatura();
        Drawable drawable = getDrawable(miniatura);
        ImageView imagen = findViewById(R.id.motominiatura);
        imagen.setImageDrawable(drawable);



        String nombremoto = myMotoViewModel.motoSelecionada(nombre).getNombre();
        TextView nom = findViewById(R.id.nombre_de_moto);
        nom.setText(nombremoto);

        int cilindrada = myMotoViewModel.motoSelecionada(nombre).getCilindrada();
        TextView cilin = findViewById(R.id.cilindrada_moto);
        cilin.setText(cilindrada + "CC");



        mimoto = new Moto();

        mimoto.setAnio(myMotoViewModel.motoSelecionada(nombre).getAnio());
        mimoto.setCilindros(myMotoViewModel.motoSelecionada(nombre).getCilindros());
        mimoto.setParMotor( myMotoViewModel.motoSelecionada(nombre).getParMotor());
        mimoto.setPeso( myMotoViewModel.motoSelecionada(nombre).getPeso());
        mimoto.setPrecio( myMotoViewModel.motoSelecionada(nombre).getPrecio());
        mimoto.setSuspension(myMotoViewModel.motoSelecionada(nombre).getSuspension());
        mimoto.setMotor(myMotoViewModel.motoSelecionada(nombre).getMotor());
        mimoto.setFrenos( myMotoViewModel.motoSelecionada(nombre).getFrenos());
        mimoto.setEspecificaciones(myMotoViewModel.motoSelecionada(nombre).getEspecificaciones());
        mimoto.setCategoria(myMotoViewModel.motoSelecionada(nombre).getCategoria());
        mimoto.setMarca(myMotoViewModel.motoSelecionada(nombre).getMarca());
        mimoto.setTipo_carnet(myMotoViewModel.motoSelecionada(nombre).getTipo_carnet());
        mimoto.setHistoria(myMotoViewModel.motoSelecionada(nombre).getHistoria());


        statsChart = findViewById(R.id.motostats);
        statsChart.setLogEnabled(false);



        TabLayout tabla = findViewById(R.id.tablayout1);
        ViewPager view = findViewById(R.id.viewPager);

        TabItem caracteristicas = findViewById(R.id.tablaCaracteristicas);
        TabItem historia = findViewById(R.id.tablaHistoria);
        configureInitialUIValues();
        updateUI();




        PagerControler myControler = new PagerControler(getSupportFragmentManager(), tabla.getTabCount(), mimoto);
        view.setAdapter(myControler);
        tabla.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    myControler.notifyDataSetChanged();
                }
                if (tab.getPosition() == 1) {
                    myControler.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabla));
    }

    private void configureInitialUIValues() {


        statsChart.getDescription().setEnabled(false);
        statsChart.setWebLineWidth(1f);
        statsChart.setWebColor(Color.LTGRAY);
        statsChart.setWebLineWidthInner(1f);
        statsChart.setWebColorInner(Color.LTGRAY);
        statsChart.setWebAlpha(100);
        statsChart.setTouchEnabled(false);

        MarkerView mv = new MarkerView(this, R.layout.fragment_caracteristicas);
        statsChart.setMarker(mv);

        statsChart.setRotationEnabled(false);

        XAxis xAxis = statsChart.getXAxis();
        xAxis.setTextSize(5f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);

        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = statsChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(5f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setDrawLabels(false);
        xAxis.setValueFormatter(new StatsValueFormatter());

        Legend l = statsChart.getLegend();
        l.setEnabled(false);
    }

    private void updateStatsChart() {

        ArrayList<RadarEntry> radarStats = new ArrayList<>();

        String nombre = getIntent().getStringExtra("nombre");
        int manejo = myMotoViewModel.motoSelecionada(nombre).getManejo();
        int aceleracion = myMotoViewModel.motoSelecionada(nombre).getManejo();
        int frenado = myMotoViewModel.motoSelecionada(nombre).getFrenado();
        int punta = myMotoViewModel.motoSelecionada(nombre).getVelPunta();
        int aerodinamica = myMotoViewModel.motoSelecionada(nombre).getAerodinamica();


        radarStats.add(new RadarEntry(manejo));
        radarStats.add(new RadarEntry(aceleracion));
        radarStats.add(new RadarEntry(frenado));
        radarStats.add(new RadarEntry(punta));
        radarStats.add(new RadarEntry(aerodinamica));

        RadarDataSet set = new RadarDataSet(radarStats, "Estadisticas");
        //set.setColor(Color.rgb(103, 110, 129));
        set.setColor(Color.rgb(255, 0, 0));
        //set.setFillColor(Color.rgb(0, 0, 255));
        set.setFillColor(Color.parseColor("#ff0000"));
        set.setDrawFilled(true);
        set.setFillAlpha(100);
        set.setLineWidth(2f);
        set.setDrawHighlightCircleEnabled(false);
        set.setDrawHighlightIndicators(false);

        RadarData data = new RadarData(set);

        data.setValueTextSize(8f);
        data.setDrawValues(false);

        statsChart.setData(data);
        statsChart.invalidate();
    }

    public void updateUI() {
        updateStatsChart();
    }

}