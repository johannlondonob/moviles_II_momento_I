package com.example.momentouno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.momentouno.Models.TarjetaModel;
import com.example.momentouno.Operations.TarjetaOperation;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class FormularioActivity extends AppCompatActivity {

    private EditText editTextNumeroTarjeta, editTextMesVencimiento, editTextAnioVencimiento, editTextCupoMax,
            editTextSaldoDisponible, editTextSaldoDeuda;
    private TextView textViewFormulario;
    private Button buttonFormularioGuardar;
    private TarjetaModel model;
    private TarjetaOperation operation;
    private Spinner spinnerFranquicias;
    private String numeroTarjeta, mesVencimiento, anioVencimiento, franquicia;
    private double cupoMax, saldoDisponible, saldoDeuda;
    private int idModel = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        final Bundle bundle = this.getIntent().getExtras();

        final String[] franquicias = new String[]{"Visa", "Mastercard", "Diner"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, franquicias);
        spinnerFranquicias = findViewById(R.id.spinnerFraquicia);
        adaptador.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFranquicias.setAdapter(adaptador);

        operation = new TarjetaOperation(getApplicationContext());
        textViewFormulario = findViewById(R.id.textViewFormulario);
        editTextNumeroTarjeta = findViewById(R.id.editTextNumeroTarjeta);
        editTextMesVencimiento = findViewById(R.id.editTextMesVencimiento);
        editTextAnioVencimiento = findViewById(R.id.editTextAnioVencimiento);
        editTextCupoMax = findViewById(R.id.editTextCupoMax);
        editTextSaldoDisponible = findViewById(R.id.editTextSaldoDisponible);
        editTextSaldoDeuda = findViewById(R.id.editTextSaldoDeuda);
        buttonFormularioGuardar = findViewById(R.id.buttonFormularioGuardar);

        textViewFormulario.setText("Formulario de creación:");
        buttonFormularioGuardar.setText("Guardar");

        if (bundle != null) {
            textViewFormulario.setText("Formulario de actualización:");
            buttonFormularioGuardar.setText("Actualizar");
            idModel = (int) bundle.getSerializable("id");
            numeroTarjeta = (String) bundle.getSerializable("numeroTarjeta");
            mesVencimiento = (String) bundle.getSerializable("mesVencimiento");
            anioVencimiento = (String) bundle.getSerializable("anioVencimiento");
            franquicia = (String) bundle.getSerializable("franquicia");
            saldoDisponible = Double.parseDouble((String) bundle.getSerializable("saldoDisponible"));
            saldoDeuda = Double.parseDouble((String) bundle.getSerializable("saldoDeuda"));
            cupoMax = Double.parseDouble((String) bundle.getSerializable("cupoMax"));

            editTextNumeroTarjeta.setText(numeroTarjeta);
            editTextMesVencimiento.setText(mesVencimiento);
            editTextAnioVencimiento.setText(anioVencimiento);
            editTextCupoMax.setText(String.valueOf(cupoMax));
            editTextSaldoDisponible.setText(String.valueOf(saldoDisponible));
            editTextSaldoDeuda.setText(String.valueOf(saldoDeuda));
        }

        buttonFormularioGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroTarjeta = editTextNumeroTarjeta.getText().toString();
                mesVencimiento = editTextMesVencimiento.getText().toString();
                anioVencimiento = editTextAnioVencimiento.getText().toString();
                franquicia = spinnerFranquicias.getSelectedItem().toString();
                cupoMax = Double.parseDouble(editTextCupoMax.getText().toString());
                saldoDisponible = Double.parseDouble(editTextSaldoDisponible.getText().toString());
                saldoDeuda = Double.parseDouble(editTextSaldoDeuda.getText().toString());

                int errores = 0;
                if (numeroTarjeta.length() < 16) {
                    Snackbar.make(v, "El número de la tarjeta tiene una longitud inferior al establecido", Snackbar.LENGTH_LONG).show();
                    editTextNumeroTarjeta.requestFocus();
                    errores++;
                } else if (Integer.parseInt(mesVencimiento) > 12) {
                    Snackbar.make(v, "La fecha no está entre los 12 meses del año", Snackbar.LENGTH_LONG).show();
                    editTextMesVencimiento.requestFocus();
                    errores++;
                } else if (Integer.parseInt(anioVencimiento) > 2200) {
                    Snackbar.make(v, "No durarás tanto. Establece un año válido", Snackbar.LENGTH_LONG).show();
                    editTextAnioVencimiento.requestFocus();
                    errores++;
                } else if (cupoMax > 1500000) {
                    Snackbar.make(v, "El cupo máximo no debe superar 1'500.000", Snackbar.LENGTH_LONG).show();
                    editTextCupoMax.requestFocus();
                    errores++;
                } else {
                    errores--;
                }

                if (mesVencimiento.length() <= 1) {
                    mesVencimiento = "0" + mesVencimiento;
                }

                if (errores <= 0) {
                    if (idModel > 0) {
                        model = new TarjetaModel(idModel, numeroTarjeta, mesVencimiento, anioVencimiento, cupoMax, saldoDisponible, saldoDeuda, franquicia);
                        long resultado = operation.actualizarTarjeta(model);
                        operation.close();

                        if (resultado > 0) {
                            limpiarCampos();
                            onBackPressed();
                            Intent intent = new Intent(FormularioActivity.this, TarjetasActivity.class);
                            intent.putExtra("msg_formulario", "La tarjeta " + numeroTarjeta + " se actualizó correctamente.");
                            startActivity(intent);
                        } else {
                            Snackbar.make(v, "No se actualizó", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        model = new TarjetaModel(numeroTarjeta, mesVencimiento, anioVencimiento, cupoMax, saldoDisponible, saldoDeuda, franquicia);
                        int resultado = operation.crearTarjeta(model);
                        operation.close();

                        if (resultado > 0) {
                            limpiarCampos();
                            onBackPressed();
                            Intent intent = new Intent(FormularioActivity.this, TarjetasActivity.class);
                            intent.putExtra("msg_formulario", "La tarjeta " + numeroTarjeta + " fue creada satisfactoriamente.");
                            startActivity(intent);
                        } else {
                            Snackbar.make(v, "No se guardó", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public void limpiarCampos() {
        editTextNumeroTarjeta.setText("");
        editTextMesVencimiento.setText("");
        editTextAnioVencimiento.setText("");
        editTextCupoMax.setText("");
        editTextSaldoDisponible.setText("");
        editTextSaldoDeuda.setText("");
    }
}
