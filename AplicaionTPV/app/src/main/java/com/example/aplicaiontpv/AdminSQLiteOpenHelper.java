package com.example.aplicaiontpv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table articulos_comandas(cod_articulo int,cod_comanda int, cantidad int)");
        db.execSQL("INSERT INTO articulos_comandas (cod_art, cod_comanda, cantidad) VALUES (101,1,3)");
        db.execSQL("INSERT INTO articulos_comandas (cod_art, cod_comanda, cantidad) VALUES (102,1,2)");
        db.execSQL("INSERT INTO articulos_comandas (cod_art, cod_comanda, cantidad) VALUES (103,2,5)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
