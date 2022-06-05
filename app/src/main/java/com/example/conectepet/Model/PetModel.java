package com.example.conectepet.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PetModel {
    private String petID;
    private String nomePet;
    private String dataNasc;
    private String especie;
    private String castrado;

    public PetModel() {
    }

    public PetModel(String nomePEt, String dataNasc, String especie, String castrado, String petID) {
        this.petID= petID;
        this.nomePet = nomePEt;
        this.dataNasc = dataNasc;
        this.especie = especie;
        this.castrado = castrado;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCastrado() {
        return castrado;
    }

    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }

    public String getPetID() {
        return petID;
    }

    public void setId(String petID) {
        this.petID = petID;
    }

    public void salvar(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("usuarios");
        reference.child("pet").child(getPetID()).setValue(this);
    }
}
