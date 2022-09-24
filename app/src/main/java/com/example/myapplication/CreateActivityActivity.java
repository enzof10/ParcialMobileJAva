package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DBClientes;

public class CreateActivityActivity extends AppCompatActivity {
        EditText txtNombre, txtApellido, txtDireccion, editTextTextEmailAddress, numberDNI, numberTelefono;
        Button buttonGuardar, buttonCreateDB;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.create_activity);

            txtNombre = findViewById(R.id.txtNombre);
            txtApellido = findViewById(R.id.txtApellido);
            txtDireccion = findViewById(R.id.txtDireccion);
            editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
            numberDNI = findViewById(R.id.numberDNI);
            numberTelefono = findViewById(R.id.numberTelefono);
            buttonGuardar = findViewById(R.id.buttonGuardar);



            buttonGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombre = txtNombre.getText().toString().trim();
                    String  apellido = txtApellido.getText().toString().trim();
                    String direccion = txtDireccion.getText().toString().trim();
                    String email = editTextTextEmailAddress.getText().toString().trim();
                    String DNI = numberDNI.getText().toString().trim();
                    String telefono = numberTelefono.getText().toString().trim();

                    if (
                            nombre.equals("")
                                    || apellido.equals("")
                                    || direccion.equals("")
                                    || email.equals("")
                                    || DNI.equals("")
                                    || telefono.equals("") )
                    {
                        Toast.makeText(com.example.myapplication.CreateActivityActivity.this, "debe completar todos los campos", Toast.LENGTH_LONG).show();
                    }else {
                        DBClientes dbClientes = new DBClientes(CreateActivityActivity.this);
                        long ClienteId = dbClientes.insertarCliente(nombre, apellido, direccion, email, telefono, DNI);
                        if(ClienteId > 0){
                            Intent intent = new Intent(com.example.myapplication.CreateActivityActivity.this, ClientesActivity.class);
                            Toast.makeText(CreateActivityActivity.this, "Cliente guardado", Toast.LENGTH_LONG).show();
                            limpiar();
                            startActivity(intent);
                        }else {
                            Toast.makeText(CreateActivityActivity.this, "Error al guardar Cliente, por favor intente de nuevo", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });

    }

    private  void  limpiar (){
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        editTextTextEmailAddress.setText("");
        numberDNI.setText("");
        numberTelefono.setText("") ;
        buttonGuardar.setText("");
    }
}