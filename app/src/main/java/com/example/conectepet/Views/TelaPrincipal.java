package com.example.conectepet.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.conectepet.R;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        getSupportActionBar().hide();

    }
}