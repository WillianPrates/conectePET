package com.example.conectepet.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.conectepet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TelaEditarCadastro extends AppCompatActivity {

    private EditText edit_nome, edit_telefone, edit_email;
    private TextView edit_mensagemBoasVindasComNome;
    private Button bt_alterar, bt_voltar;
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

    }

    private void buscaUsuarioBancoDeDados() {
        DatabaseReference reference = database.getReference();
    }

    public void iniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_telefone = findViewById(R.id.edit_telefone);
        edit_mensagemBoasVindasComNome = findViewById(R.id.textEditTextComNome);

        bt_alterar = findViewById(R.id.bt_editarDados);
        bt_voltar = findViewById(R.id.bt_VoltarEditarCadastroParaTelaInicial);
    }
}