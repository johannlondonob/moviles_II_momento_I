package com.example.momentouno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.momentouno.Models.TarjetaModel;
import com.example.momentouno.Operations.TarjetaOperation;
import com.google.android.material.snackbar.Snackbar;

public class DetalleActivity extends AppCompatActivity {
    private TextView textViewNumeroTarjeta, textViewMesVencimiento, textViewAnioVencimiento, textViewCupoMax,
            textViewSaldoDisponible, textViewSaldoDeuda, textViewFranquicia;
    private Button buttonDetalleEditar;
    private Button buttonDetalleEliminar;
    private TarjetaOperation operation;
    private TarjetaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        operation = new TarjetaOperation(getApplicationContext());

        textViewNumeroTarjeta = findViewById(R.id.textViewNumeroTarjeta);
        textViewMesVencimiento = findViewById(R.id.textViewMesVencimiento);
        textViewAnioVencimiento = findViewById(R.id.textViewAnioVencimiento);
        textViewCupoMax = findViewById(R.id.textViewCupoMax);
        textViewSaldoDisponible = findViewById(R.id.textViewSaldoDisponible);
        textViewSaldoDeuda = findViewById(R.id.textViewSaldoDeuda);
        textViewFranquicia = findViewById(R.id.textViewFranquicia);
        buttonDetalleEliminar = findViewById(R.id.buttonDetalleEliminar);
        buttonDetalleEditar = findViewById(R.id.buttonDetalleEditar);

        model = (TarjetaModel) getIntent().getSerializableExtra("item");

        final String numeroTarjeta = model.get_numeroTarjeta();
        final String mesVencimiento = model.get_mesVencimiento();
        final String anioVencimiento = model.get_anioVencimiento();
        final String cupoMax = String.valueOf(model.get_cupoMax());
        final String saldoDisponible = String.valueOf(model.get_saldoDisponible());
        final String saldoDeuda = String.valueOf(model.get_saldoDeuda());
        final String franquicia = model.get_franquicia();

        textViewNumeroTarjeta.setText(numeroTarjeta);
        textViewMesVencimiento.setText(mesVencimiento);
        textViewAnioVencimiento.setText(anioVencimiento);
        textViewCupoMax.setText(cupoMax);
        textViewSaldoDisponible.setText(saldoDisponible);
        textViewSaldoDeuda.setText(saldoDeuda);
        textViewFranquicia.setText(franquicia);

        buttonDetalleEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                int resultado = operation.borrarTarjeta(model.get_id());

                if (resultado > 0) {
                    Intent intent = new Intent(DetalleActivity.this, TarjetasActivity.class);
                    intent.putExtra("msg", "Se eliminó correctamente la tarjeta");
                    startActivity(intent);
                } else {
                    Snackbar.make(v, "No se pudo eliminar la tarjeta" + model.get_id(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        buttonDetalleEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String auxiliarNumeroTarjeta = "";
                for (int i = 0; i <= numeroTarjeta.length() + 1; i++) {
                    if (i != 0){
                        if (i % 4 == 0) {
                            int index1 = i - 4;
                            auxiliarNumeroTarjeta += numeroTarjeta.substring(index1, i) + "";
                        }
                    }
                }
                onBackPressed();
                Intent intent = new Intent(DetalleActivity.this, FormularioActivity.class);
                intent.putExtra("id", model.get_id());
                intent.putExtra("numeroTarjeta", auxiliarNumeroTarjeta);
                intent.putExtra("mesVencimiento", mesVencimiento);
                intent.putExtra("anioVencimiento", anioVencimiento);
                intent.putExtra("cupoMax", cupoMax);
                intent.putExtra("saldoDisponible", saldoDisponible);
                intent.putExtra("saldoDeuda", saldoDeuda);
                intent.putExtra("franquicia", franquicia);
                startActivity(intent);
            }
        });
    }
}
