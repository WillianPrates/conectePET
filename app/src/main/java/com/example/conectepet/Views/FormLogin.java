package com.example.conectepet.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.conectepet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button bt_entrar;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private CheckBox mostrarSenha;
    String[] mensagens = {"Preencha todos os camppos", "Login efetuado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        getSupportActionBar().hide();
        iniciarComponentes();

        text_tela_cadastro.setOnClickListener(view -> {
            Intent intent = new Intent(FormLogin.this, FormCadastro.class);
            startActivity(intent);
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){

                    Snackbar snackbar = Snackbar.make(view, mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    AutenticarUsuario(view);
                }

            }
        });

        mostrarSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if (isCheked){
                    edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void AutenticarUsuario(View view){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    }, 3000);

                }else{
                    String erro;
                    try {

                        throw task.getException();

                    }catch (Exception e){

                        erro = "Erro ao fazer o login.";

                    }
                    Snackbar snackbar = Snackbar.make(view, erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });

    }

    private void TelaPrincipal(){
        Intent intent = new Intent(FormLogin.this, TelaPrincipal.class);
        startActivity(intent);
        finish();
    }

    private void iniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        progressBar = findViewById(R.id.progressBar);
        mostrarSenha = findViewById(R.id.checkBoxSenha);

    }

}