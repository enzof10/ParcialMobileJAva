package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DBClientes;
import com.example.myapplication.entidadades.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {
    EditText txtNombre, txtApellido, txtDireccion, editTextTextEmailAddress, numberDNI, numberTelefono;
    Button buttonGuardar;
    Clientes cliente;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtDireccion = findViewById(R.id.txtDireccion);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        numberDNI = findViewById(R.id.numberDNI);
        numberTelefono = findViewById(R.id.numberTelefono);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DBClientes dbClientes = new DBClientes(EditarActivity.this );
        cliente = dbClientes.verCliente(id);

        if (cliente != null) {
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
            txtDireccion.setText(cliente.getDireccion());
            editTextTextEmailAddress.setText(cliente.getEmail());
            numberDNI.setText(cliente.getDni());
            numberTelefono.setText(cliente.getTelefono());
        }

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombre = txtNombre.getText().toString().trim();
                    String  apellido = txtApellido.getText().toString().trim();
                    String direccion = txtDireccion.getText().toString().trim();
                    String email = editTextTextEmailAddress.getText().toString().trim();
                    String DNI = numberDNI.getText().toString().trim();
                    String telefono = numberTelefono.getText().toString().trim();
                    Toast.makeText(EditarActivity.this, "id: "+  id+ " nombre : "+ nombre , Toast.LENGTH_LONG).show();
                    if (
                            nombre.equals("")
                                    || apellido.equals("")
                                    || direccion.equals("")
                                    || email.equals("")
                                    || DNI.equals("")
                                    || telefono.equals("") )
                    {
                        Toast.makeText(EditarActivity.this, "debe completar todos los campos", Toast.LENGTH_LONG).show();
                    }else {
                        correcto =  dbClientes.editarCliente(id, nombre, apellido, direccion, email, telefono, DNI);
                        if (correcto){
                            Toast.makeText(EditarActivity.this, "Cliente editado", Toast.LENGTH_LONG).show();
                            verClientes();
                        }else {
                            Toast.makeText(EditarActivity.this, "Hubo un problema al editar el cliente, por favor intente de nuevo", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });


    }

    private  void verClientes(){
        Intent intent = new Intent(this, ClientesActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}