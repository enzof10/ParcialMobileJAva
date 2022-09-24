package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    private  static  final int DATABASE_VERSION = 2;
    private  static  final String DATABASE_NOMBRE = "parcial.db";
    public   static  final String TABLE_CLIENTES = "t_clientes";


    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTES +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "DNI TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTES);
        onCreate(sqLiteDatabase);
    }
}
