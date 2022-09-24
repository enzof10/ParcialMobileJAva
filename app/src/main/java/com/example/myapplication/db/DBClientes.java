package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.EditarActivity;
import com.example.myapplication.entidadades.Clientes;

import java.util.ArrayList;

public class DBClientes extends  DBhelper {
    Context context;

    public DBClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCliente(String nombre, String apellido, String direccion, String email, String telefono,String DNI ){
        long id = 0;
        try {
            DBhelper dBhelper = new DBhelper(context);
            SQLiteDatabase db = dBhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("direccion", direccion);
            values.put("email", email);
            values.put("telefono", telefono);
            values.put("DNI", DNI);

            id = db.insert(TABLE_CLIENTES, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return  id;
    }

    public ArrayList<Clientes> mostrarClientes(){
        DBhelper dBhelper = new DBhelper(context);
        SQLiteDatabase db = dBhelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente = null;
        Cursor cursorClientes = null;

        cursorClientes = db.rawQuery("SELECT * FROM "+ TABLE_CLIENTES, null);
        if (cursorClientes.moveToFirst()){
            do {
                cliente = new Clientes();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setNombre(cursorClientes.getString(1));
                cliente.setApellido(cursorClientes.getString(2));
                cliente.setDireccion(cursorClientes.getString(3));
                cliente.setTelefono(cursorClientes.getString(4));
                cliente.setEmail(cursorClientes.getString(5));
                cliente.setDni(cursorClientes.getString(6));
                listaClientes.add(cliente);
            } while (cursorClientes.moveToNext());
        }
        cursorClientes.close();
        return listaClientes;
    }


    public Clientes verCliente(Integer id){
        DBhelper dBhelper = new DBhelper(context);
        SQLiteDatabase db = dBhelper.getWritableDatabase();

        Clientes cliente = null;
        Cursor cursorClientes = null;

        cursorClientes = db.rawQuery("SELECT * FROM "+ TABLE_CLIENTES + " WHERE id = " + id + " LIMIT 1", null);
        if (cursorClientes.moveToFirst()){
                cliente = new Clientes();
                cliente.setId(cursorClientes.getInt(0));
                cliente.setNombre(cursorClientes.getString(1));
                cliente.setApellido(cursorClientes.getString(2));
                cliente.setDireccion(cursorClientes.getString(3));
                cliente.setTelefono(cursorClientes.getString(4));
                cliente.setEmail(cursorClientes.getString(5));
                cliente.setDni(cursorClientes.getString(6));
        }
        cursorClientes.close();
        return cliente;
    }


    public boolean editarCliente( Integer id, String nombre, String apellido, String direccion, String email, String telefono,String DNI ){
        boolean correcto = false;

        DBhelper dBhelper = new DBhelper(context);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_CLIENTES + " SET nombre = '"+nombre+"', apellido = '"+apellido+"', direccion = '"+direccion+"', email = '"+email+"', telefono = '"+telefono+"', DNI = '"+DNI+"' WHERE id = '"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return  correcto;
    }

    public boolean eliminarCliente( Integer id){
        boolean correcto = false;

        DBhelper dBhelper = new DBhelper(context);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_CLIENTES + " WHERE id = '"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return  correcto;
    }


}
