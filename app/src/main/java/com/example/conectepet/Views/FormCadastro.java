package com.example.conectepet.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.conectepet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {

    private EditText cadastro_email, cadastro_nome, cadastro_senha, cadastro_confirmaSenha;
    private Button bt_cadastrar;
    private TextView text_possui_cadastro;
    String[] mensagens = {"Preencha todos os camppos", "Cadastro realizado com sucesso","As senhas não são iguais"};
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        getSupportActionBar().hide();
        IniciarComponentes();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = cadastro_nome.getText().toString();
                String senha = cadastro_senha.getText().toString();
                String confirmaSenha = cadastro_confirmaSenha.getText().toString();
                String email = cadastro_email.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
                        Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();

                } else if(senha.equals(confirmaSenha)){
                    CadastrarUsuario(view);
                }else{
                    Snackbar snackbar = Snackbar.make(view, mensagens[2], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });

        text_possui_cadastro.setOnClickListener(view -> {
            Intent intent = new Intent(FormCadastro.this, FormLogin.class);
            startActivity(intent);
        });

    }

    private void CadastrarUsuario(View view) {
        String email = cadastro_email.getText().toString();
        String senha = cadastro_senha.getText().toString();
        String confirmSenha = cadastro_confirmaSenha.getText().toString();


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SalvarDadosUsuario();
                    Snackbar snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    String erro;
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "A senha não atende os requisitos de 6 caracteres";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Este email já está em uso. ";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "Email não atende os requisitos de email";
                    } catch (Exception e) {
                        erro = "Erro desconhecido ao cadastrar o usuário";
                    }

                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });
    }

    private void SalvarDadosUsuario() {
        String nome = cadastro_nome.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", " Sucesso ao salvar os dados");

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error", " Erro ao salvar os dados" + e.toString());

                    }
                });

    }

    private void IniciarComponentes() {
        cadastro_nome = findViewById(R.id.cadastro_nome);
        cadastro_email = findViewById(R.id.cadastro_email);
        cadastro_senha = findViewById(R.id.cadastro_senha);
        cadastro_confirmaSenha = findViewById(R.id.edit_confirm_senha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
        text_possui_cadastro = findViewById(R.id.text_possui_cadastro);

    }

}