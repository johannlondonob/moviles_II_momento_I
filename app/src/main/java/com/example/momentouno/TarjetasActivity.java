package com.example.momentouno;

import android.content.Intent;
import android.os.Bundle;

import com.example.momentouno.Adapters.TarjetaAdapter;
import com.example.momentouno.Models.TarjetaModel;
import com.example.momentouno.Operations.TarjetaOperation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class TarjetasActivity extends AppCompatActivity {

    private FloatingActionButton fab_crear_tarjeta;
    private ListView listViewTarjetas;
    private TarjetaOperation operation;
    private ArrayList<TarjetaModel> tarjetas;
    private TarjetaAdapter adapter;
    private View snackEliminado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Bundle bundle = this.getIntent().getExtras();
        setSupportActionBar(toolbar);

        snackEliminado = findViewById(R.id.viewSnackEliminado);

        if (bundle != null) {
            String mensaje = (String) bundle.getSerializable("msg");
            Snackbar.make(snackEliminado, mensaje, Snackbar.LENGTH_LONG).show();
        }

        fab_crear_tarjeta = findViewById(R.id.fab_crear_tarjeta);
        listViewTarjetas = findViewById(R.id.listViewTarjetas);
        operation = new TarjetaOperation(getApplicationContext());

        tarjetas = operation.seleccionarTarjetas();
        operation.close();

        adapter = new TarjetaAdapter(tarjetas, getApplicationContext());
        listViewTarjetas.setAdapter(adapter);

        listViewTarjetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TarjetaModel selecto = tarjetas.get(position);
                Intent detalle = new Intent(TarjetasActivity.this, DetalleActivity.class);
                detalle.putExtra("item", selecto);
                startActivity(detalle);
            }
        });

        fab_crear_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent = new Intent(TarjetasActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

    }

}
