package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class VerActivity extends AppCompatActivity {
    EditText txtNombre, txtApellido, txtDireccion, editTextTextEmailAddress, numberDNI, numberTelefono;
    Button buttonGuardar;
    FloatingActionButton fabEditar, fabEliminar;

    Clientes cliente;
    int id = 0;

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

        DBClientes dbClientes = new DBClientes(VerActivity.this );
        cliente = dbClientes.verCliente(id);

        if (cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
            txtDireccion.setText(cliente.getDireccion());
            editTextTextEmailAddress.setText(cliente.getEmail());
            numberDNI.setText(cliente.getDni());
            numberTelefono.setText(cliente.getTelefono());
            buttonGuardar.setVisibility(View.INVISIBLE);

            txtNombre.setInputType(InputType.TYPE_NULL);
            txtApellido.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            editTextTextEmailAddress.setInputType(InputType.TYPE_NULL);
            numberDNI.setInputType(InputType.TYPE_NULL);
            numberTelefono.setInputType(InputType.TYPE_NULL);

        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (dbClientes.eliminarCliente(id)){
                                    Toast.makeText(VerActivity.this, "cliente con id" + id+ " eliminado con exito"  , Toast.LENGTH_LONG).show();
                                    irAlista();
                                }else {
                                    Toast.makeText(VerActivity.this, "No se pudo eliminar el cliente, por favor intente de nuevo"  , Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });

    }

    private void irAlista(){
        Intent intent = new Intent(this , ClientesActivity.class);
        startActivity(intent);
    }
}