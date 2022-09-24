package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DBhelper;

public class MainActivity extends AppCompatActivity {

    Button buttonCreateDB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCreateDB = findViewById(R.id.buttonCreateDB);

        buttonCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhelper dBhelper = new DBhelper(MainActivity.this);
                SQLiteDatabase db = dBhelper.getWritableDatabase();
                if(db != null){
                    Intent intent = new Intent( MainActivity.this, ClientesActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "base de datos creada", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "no se pudo crear la base de datos", Toast.LENGTH_LONG).show();
                }
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