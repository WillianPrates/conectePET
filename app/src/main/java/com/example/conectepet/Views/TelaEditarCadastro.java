package com.example.conectepet.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.conectepet.R;

public class TelaEditarCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_cadastro);
        getSupportActionBar().hide();
        iniciarComponentes();
    }

    public void iniciarComponentes(){

    }
}