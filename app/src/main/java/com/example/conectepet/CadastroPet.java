package com.example.conectepet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.conectepet.Views.TelaPrincipal;

public class CadastroPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        getSupportActionBar().hide();
        Button buttonVoltar;
        buttonVoltar = findViewById(R.id.buttonVoltarParaTelaInicial);

        buttonVoltar.setOnClickListener(v ->{
            Intent intent = new Intent(CadastroPet.this, TelaPrincipal.class);
            startActivity(intent);
        });

    }
}