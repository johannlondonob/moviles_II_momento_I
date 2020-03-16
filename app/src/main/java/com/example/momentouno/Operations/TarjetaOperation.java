package com.example.momentouno.Operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.momentouno.Database.SQLHelper;
import com.example.momentouno.Models.TarjetaModel;

import java.util.ArrayList;

public class TarjetaOperation {
    private static final String NOMBRE_BASE_DE_DATOS = "cesde.db";
    private static final String NOMBRE_TABLA = "tarjeta";
    private static final int VERSION_DB = 1;
    public static SQLiteDatabase database;
    public final Context context;
    private static SQLHelper helper;
    private TarjetaModel model;

    public TarjetaOperation(Context context) {
        this.context = context;
        helper = new SQLHelper(context, NOMBRE_BASE_DE_DATOS, null, VERSION_DB);
    }

    public void openRead() {
        database = helper.getReadableDatabase();
    }

    public void openWrite() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public int crearTarjeta(TarjetaModel model) {
        try {
            ContentValues values = new ContentValues();
            values.put("numero_tarjeta", model.get_numeroTarjeta());
            values.put("mes_vencimiento", model.get_mesVencimiento());
            values.put("anio_vencimiento", model.get_anioVencimiento());
            values.put("cupo_max", model.get_cupoMax());
            values.put("saldo_disponible", model.get_saldoDisponible());
            values.put("saldo_deuda", model.get_saldoDeuda());
            values.put("franquicia", model.get_franquicia());

            openWrite();
            return (int) database.insert(NOMBRE_TABLA, null, values);

        } catch (Exception e) {
            String TAG = "MyActivity";
            Log.i(TAG, "e" + e);
            return -1;
        }
    }

    public int borrarTarjeta(int id) {
        try {
            String sqlWhere = "id = ?";
            String idString = String.valueOf(id);
            String[] whereArgs = new String[]{idString};
            openWrite();
            return database.delete(NOMBRE_TABLA, sqlWhere, whereArgs);
        } catch (Exception e) {
            return -1;
        }
    }

    public int actualizarTarjeta(TarjetaModel model) {
        try {
            String sqlWhere = "id = ?";
            String idString = String.valueOf(model.get_id());
            String[] whereArgs = new String[]{idString};

            ContentValues values = new ContentValues();
            values.put("numero_tarjeta", model.get_numeroTarjeta());
            values.put("mes_vencimiento", model.get_mesVencimiento());
            values.put("anio_vencimiento", model.get_anioVencimiento());
            values.put("cupo_max", model.get_cupoMax());
            values.put("saldo_disponible", model.get_saldoDisponible());
            values.put("saldo_deuda", model.get_saldoDeuda());
            values.put("franquicia", model.get_franquicia());

            openWrite();
            return (int) database.update(NOMBRE_TABLA, values, sqlWhere, whereArgs);
        } catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<TarjetaModel> seleccionarTarjetas() {
        ArrayList<TarjetaModel> tarjetasString = new ArrayList<>();

        try {
            openRead();
            Cursor cursor = database.query(NOMBRE_TABLA, null, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    int id;
                    String numeroTarjeta, mesVencimiento, anioVencimiento, franquicia;
                    double cupoMax, saldoDisponible, saldoDeuda;

                    id = cursor.getInt(cursor.getColumnIndex("id"));
                    numeroTarjeta = cursor.getString(cursor.getColumnIndex("numero_tarjeta"));
                    mesVencimiento = cursor.getString(cursor.getColumnIndex("mes_vencimiento"));
                    anioVencimiento = cursor.getString(cursor.getColumnIndex("anio_vencimiento"));
                    franquicia = cursor.getString(cursor.getColumnIndex("franquicia"));
                    cupoMax = cursor.getDouble(cursor.getColumnIndex("cupo_max"));
                    saldoDisponible = cursor.getDouble(cursor.getColumnIndex("saldo_disponible"));
                    saldoDeuda = cursor.getDouble(cursor.getColumnIndex("saldo_deuda"));

                    TarjetaModel model = new TarjetaModel(id, numeroTarjeta, mesVencimiento, anioVencimiento, cupoMax, saldoDisponible, saldoDeuda, franquicia);
                    tarjetasString.add(model);
                }
            }

            return tarjetasString;
        } catch (Exception e) {
            return tarjetasString;
        }
    }
}
