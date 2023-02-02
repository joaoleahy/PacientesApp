package com.example.pacientesapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    PacienteDbHelper DB;
    RecyclerView recyclerView;
    CustomAdapter adapter;


    private ArrayList<String> id;
    private ArrayList<String> nome;
    private ArrayList<String> idade;
    private ArrayList<String> temperatura;
    private ArrayList<String> tosse;
    private ArrayList<String> dor;
    private ArrayList<String> pais;
    private ArrayList<String> semanas;
    private ArrayList<String> status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        DB = new PacienteDbHelper(this);
        id = new ArrayList<>();
        nome = new ArrayList<>();
        idade = new ArrayList<>();
        temperatura = new ArrayList<>();
        tosse = new ArrayList<>();
        dor = new ArrayList<>();
        pais = new ArrayList<>();
        semanas = new ArrayList<>();
        status = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);

        adapter = new CustomAdapter(this,id,nome,idade,temperatura,tosse,dor,pais,semanas,status);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        storeDatainArrays();



    }
    void storeDatainArrays(){
        Cursor cursor = DB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                nome.add(cursor.getString(1));
                idade.add(cursor.getString(2));
                temperatura.add(cursor.getString(3));
                tosse.add(cursor.getString(4));
                dor.add(cursor.getString(5));
                pais.add(cursor.getString(6));
                semanas.add(cursor.getString(7));
                status.add(cursor.getString(8));
            }
        }
    }

    


}