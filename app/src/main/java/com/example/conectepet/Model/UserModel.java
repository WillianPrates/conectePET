package com.example.conectepet.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserModel {
    private String id;
    private String nome;
    private String email;
    private String telefone;

    public UserModel() {
    }

    public UserModel(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UserModel(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void salvarDados(){
        System.out.println("Chegou aqui");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(getEmail()).setValue(this);
    }
}