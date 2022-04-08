package com.example.conectepet.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.conectepet.CadastroPet;
import com.example.conectepet.R;
import com.google.firebase.auth.FirebaseAuth;

public class TelaPrincipal extends AppCompatActivity {

    Button menuButton, cadastrarPetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        getSupportActionBar().hide();

        iniciarComponentes();

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(TelaPrincipal.this, v);
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
                                Intent intent = new Intent(TelaPrincipal.this, FormLogin.class);
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
        cadastrarPetButton.setOnClickListener(v ->{
            Intent intent = new Intent(TelaPrincipal.this, CadastroPet.class);
            startActivity(intent);
        });
        }

    private void iniciarComponentes() {
        menuButton = findViewById(R.id.buttonUser);
        cadastrarPetButton = findViewById(R.id.buttonCadastrarPet);

    }
}