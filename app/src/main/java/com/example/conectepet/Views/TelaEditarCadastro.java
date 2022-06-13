package com.example.conectepet.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conectepet.Model.PetModel;
import com.example.conectepet.Model.UserModel;
import com.example.conectepet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class TelaEditarCadastro extends AppCompatActivity {

    private EditText edit_nome, edit_telefone, edit_email;
    private TextView edit_mensagemBoasVindasComNome;
    private Button bt_alterar, bt_voltar, menuButton;
    private String userID;

    private FirebaseDatabase database;
    private ValueEventListener ValueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_cadastro);
        getSupportActionBar().hide();
        iniciarComponentes();
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        buscaUsuarioBancoDeDados();

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(TelaEditarCadastro.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item_editar_conta:
                                Toast.makeText(getApplicationContext(),"Opção de edição de conta", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(TelaEditarCadastro.this, TelaEditarCadastro.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_sair:
                                FirebaseAuth.getInstance().signOut();
                                Intent intent2 = new Intent(TelaEditarCadastro.this, FormLogin.class);
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


    private void buscaUsuarioBancoDeDados() {
        ArrayList<UserModel> lista;
        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        lista = new ArrayList<UserModel>();
        UserModel userModel = new UserModel();

    }

    public void iniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_telefone = findViewById(R.id.edit_telefone);
        edit_mensagemBoasVindasComNome = findViewById(R.id.textEditTextComNome);

        menuButton = findViewById(R.id.buttonUserTelaEditaCadastro);

        bt_alterar = findViewById(R.id.bt_editarDados);
        bt_voltar = findViewById(R.id.bt_VoltarEditarCadastroParaTelaInicial);
    }
}