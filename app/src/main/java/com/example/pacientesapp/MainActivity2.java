package com.example.pacientesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    EditText idnome, idnasc,idtemp,idtosse,iddor,idsem;
    RadioGroup radioGroup;
    RadioButton radioButton;
    PacienteDbHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        idnome = findViewById(R.id.idnome);
        idnasc = findViewById(R.id.idnasc);
        idtemp = findViewById(R.id.idtemp);
        idtosse = findViewById(R.id.idtosse);
        iddor  = findViewById(R.id.iddor);
        idsem = findViewById(R.id.idsem);
        radioGroup = findViewById(R.id.radioGroup);

        DB = new PacienteDbHelper(this);



    }
    public void btnAdd(View v) {
        String nomep = idnome.getText().toString().toLowerCase();
        String idadep = idnasc.getText().toString();
        String temperaturap = idtemp.getText().toString();
        String tossep = idtosse.getText().toString();
        String dorp = iddor.getText().toString();
        String semanap = idsem.getText().toString();

        if(nomep.isEmpty() || idadep.isEmpty() || temperaturap.isEmpty() || tossep.isEmpty() || dorp.isEmpty() || semanap.isEmpty()){

            Snackbar.make(v,"Você esqueceu de preencher um campo",Snackbar.LENGTH_SHORT).show();

        }else{
            btc(v);
            RadioButtonSelecionar(v);
        }


    }


    public void btc(View view){
        String nomep = idnome.getText().toString().toLowerCase();
        PacienteDbHelper myDB = new PacienteDbHelper(MainActivity2.this);
        myDB.validate(nomep);
    }



    private void RadioButtonSelecionar(View view) {
        String nome = idnome.getText().toString().toLowerCase();
        int idade = Integer.parseInt(idnasc.getText().toString());
        int temperatura = Integer.parseInt(idtemp.getText().toString());
        int tosse = Integer.parseInt(idtosse.getText().toString());
        int dor = Integer.parseInt(iddor.getText().toString());
        int semana = Integer.parseInt(idsem.getText().toString());

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String paisp = radioButton.getText().toString();
        String[] items = {"Itália","China","Indonésia","Portugal","EUA"};
        List<String> lista = Arrays.asList(items);

        if (lista.contains(paisp) && semana <= 6 && temperatura > 37 && tosse > 5 && dor > 5) {
            DB.addTB(nome.toLowerCase(), idade, temperatura, tosse, dor, paisp, semana, "Paciente deve ser internado para tratamento");
        } else if ((lista.contains(paisp) && semana <= 6 && (idade < 10 || idade > 60) && (temperatura > 37 || dor > 3 || tosse > 5)) ||
                (lista.contains(paisp) && semana <= 6 && dor > 5 && tosse > 5 && temperatura > 37 && idade >= 10 && idade <= 60)) {
            DB.addTB(nome.toLowerCase(), idade, temperatura, tosse, dor, paisp, semana, "Paciente deve ser enviado à quarentena");
        } else {
            DB.addTB(nome.toLowerCase(), idade, temperatura, tosse, dor, paisp, semana, "Paciente Liberado");
        }



    }


}
