package com.example.pacientesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class UpdateActivity extends AppCompatActivity {
    private TextView id_nome;
    private TextView id_idade;
    private TextView id_temperatura;
    private TextView id_tosse;
    private TextView id_dor;
    private TextView id_pais;
    private TextView id_semanas;
    private TextView id_status;

    private Button update_button;
    private Button deletar_update;

    private String nome;
    private String idade;
    private String temperatura;
    private String tosse;
    private String dor;
    private String pais;
    private String semanas;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        String id;
        getIntent().hasExtra("id");
        id = getIntent().getStringExtra("id");

        id_nome = findViewById(R.id.id_nome_edit);
        id_idade = findViewById(R.id.id_idade_edit);
        id_temperatura= findViewById(R.id.id_temperatura_edit);
        id_tosse = findViewById(R.id.id_tosse_edit);
        id_dor = findViewById(R.id.id_dor_edit);
        id_pais = findViewById(R.id.id_pais_edit);
        id_semanas = findViewById(R.id.id_semanas_edit);
        id_status = findViewById(R.id.id_status_edit);
        update_button = findViewById(R.id.update_button);
        deletar_update = findViewById(R.id.deletar_button);

        getIntentData();
        update_button.setOnClickListener(view -> {
            try {
                nome = id_nome.getText().toString().toLowerCase().trim();
                idade = id_idade.getText().toString().trim();
                temperatura = id_temperatura.getText().toString().trim();
                tosse = id_tosse.getText().toString().trim();
                dor = id_dor.getText().toString().trim();
                pais = id_pais.getText().toString().trim();
                semanas = id_semanas.getText().toString().trim();
                status = id_status.getText().toString().trim();
                PacienteDbHelper myDB = new PacienteDbHelper(UpdateActivity.this);
                myDB.updateData(id, nome, idade, temperatura, tosse, dor, pais, semanas, status);
                Snackbar.make(view, "Paciente atualizado com sucesso!", Snackbar.LENGTH_SHORT).show();
            }catch (Exception error1){
                Snackbar.make(view, "Esse paciente já foi cadastrado", Snackbar.LENGTH_SHORT).show();
                error1.printStackTrace();
            }

            });
        deletar_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PacienteDbHelper myDB = new PacienteDbHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
            }
        });





    }



    void getIntentData() {
        if (getIntent().hasExtra("nome") &&
                getIntent().hasExtra("idade") &&
                getIntent().hasExtra("temperatura") &&
                getIntent().hasExtra("tosse")&&
                getIntent().hasExtra("dor") &&
                getIntent().hasExtra("país") &&
                getIntent().hasExtra("semanas")&&
                getIntent().hasExtra("status")) {

            nome = getIntent().getStringExtra("nome");
            idade = getIntent().getStringExtra("idade");
            temperatura = getIntent().getStringExtra("temperatura");
            tosse = getIntent().getStringExtra("tosse");
            dor = getIntent().getStringExtra("dor");
            pais = getIntent().getStringExtra("país");
            semanas = getIntent().getStringExtra("semanas");
            status = getIntent().getStringExtra("status");

            id_nome.setText(nome.toLowerCase());
            id_idade.setText(idade);
            id_temperatura.setText(temperatura);
            id_tosse.setText(tosse);
            id_dor.setText(dor);
            id_pais.setText(pais);
            id_semanas.setText(semanas);
            id_status.setText(status);



        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}