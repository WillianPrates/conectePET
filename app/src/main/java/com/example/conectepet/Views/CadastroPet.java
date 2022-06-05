package com.example.conectepet.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.conectepet.Model.PetModel;
import com.example.conectepet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;

import java.util.UUID;

public class CadastroPet extends AppCompatActivity {

    Button buttonVoltar, menuButton, buttonCadastrarPet;

    private EditText petID, nomePet, dataNasc, especie, castrado;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        getSupportActionBar().hide();
        iniciarComponentes();

        PetModel petModel = new PetModel();



        buttonCadastrarPet.setOnClickListener(v ->{
            String uniqueID = UUID.randomUUID().toString();
            petModel.setId(uniqueID);
            petModel.setNomePet(nomePet.getText().toString());
            petModel.setDataNasc(dataNasc.getText().toString());
            petModel.setEspecie(especie.getText().toString());
            petModel.setCastrado(castrado.getText().toString());
            petModel.salvar();
            Intent intent = new Intent(CadastroPet.this, TelaPrincipal.class);
            startActivity(intent);
        });

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
                                Intent intent = new Intent(CadastroPet.this, TelaEditarCadastro.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_sair:
                                FirebaseAuth.getInstance().signOut();
                                Intent intent2 = new Intent(CadastroPet.this, FormLogin.class);
                                startActivity(intent2);
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
        nomePet = findViewById(R.id.cadastroNomePet);
        dataNasc = findViewById(R.id.cadastroNascPet);
        especie = findViewById(R.id.cadastroEspeciePet);
        castrado = findViewById(R.id.cadastroCastrado);

        buttonVoltar = findViewById(R.id.buttonVoltarParaTelaInicial);
        buttonCadastrarPet = findViewById(R.id.buttonCadastrarPet);
        menuButton = findViewById(R.id.buttonUserTelaCadastro);

    }
}