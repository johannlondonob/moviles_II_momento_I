package com.example.momentouno.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tarjeta " +
                "(" +
                "id INTEGER primary key autoincrement," +
                "numero_tarjeta TEXT," +
                "mes_vencimiento TEXT," +
                "anio_vencimiento TEXT," +
                "cupo_max REAL," +
                "saldo_disponible REAL," +
                "saldo_deuda REAL," +
                "franquicia TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tarjeta");
        onCreate(db);
    }
}
