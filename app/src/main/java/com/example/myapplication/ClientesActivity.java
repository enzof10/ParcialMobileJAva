package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.adaptadores.ListaClientesAdaptador;
import com.example.myapplication.db.DBClientes;
import com.example.myapplication.entidadades.Clientes;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {

    Button btnNuevoCliente;
    RecyclerView listaClientes_lista;
    ArrayList<Clientes> clientesArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);
        listaClientes_lista = findViewById(R.id.listaClientes_lista);
        listaClientes_lista.setLayoutManager(new LinearLayoutManager(this));

        DBClientes dbClientes = new DBClientes(ClientesActivity.this);

        clientesArrayList = new ArrayList<>();

        ListaClientesAdaptador listaClientesAdaptador = new ListaClientesAdaptador(dbClientes.mostrarClientes());
        listaClientes_lista.setAdapter(listaClientesAdaptador);

        btnNuevoCliente = findViewById(R.id.btnNuevoCliente);

        btnNuevoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ClientesActivity.this ,CreateActivityActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_clientes, menu);
        return true;
    }

    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoCliente();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private  void nuevoCliente(){
        Intent intent = new Intent(this, CreateActivityActivity.class);
        startActivity(intent);
    }
}