package com.example.conectepet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.conectepet.Views.FormLogin;
import com.example.conectepet.Views.TelaPrincipal;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroPet extends AppCompatActivity {

    Button buttonVoltar, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        getSupportActionBar().hide();
        iniciarComponentes();



        buttonVoltar.setOnClickListener(v ->{
            Intent intent = new Intent(CadastroPet.this, TelaPrincipal.class);
            startActivity(intent);
        });
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(CadastroPet.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item_editar_conta:
                                Toast.makeText(getApplicationContext(),"Opção de edição de conta", Toast.LENGTH_LONG).show();
                                return true;

                            case R.id.item_sair:
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(CadastroPet.this, FormLogin.class);
                                startActivity(intent);
                                return true;

                            default:
                                return false;

                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    public void iniciarComponentes(){
        buttonVoltar = findViewById(R.id.buttonVoltarParaTelaInicial);
        menuButton = findViewById(R.id.buttonUserTelaCadastro);

    }
}