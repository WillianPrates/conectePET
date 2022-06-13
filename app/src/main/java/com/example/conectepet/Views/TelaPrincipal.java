package com.example.conectepet.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conectepet.Adapter.PetAdapter;
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
import java.util.List;

public class TelaPrincipal extends AppCompatActivity {

    Button menuButton, cadastrarPetButton;
    ListView listPets;
    UserModel userModel = new UserModel();
    TextView textOla;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        getSupportActionBar().hide();
        iniciarComponentes();
        trazDadosBD();

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
                                Intent intent = new Intent(TelaPrincipal.this, TelaEditarCadastro.class);
                                startActivity(intent);
                                return true;

                            case R.id.item_sair:
                                FirebaseAuth.getInstance().signOut();
                                Intent intent2 = new Intent(TelaPrincipal.this, FormLogin.class);
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

        cadastrarPetButton.setOnClickListener(v ->{
            Intent intent = new Intent(TelaPrincipal.this, CadastroPet.class);
            startActivity(intent);
        });
        }

    private void trazDadosBD() {
        FirebaseDatabase database;
        ValueEventListener valueEventListener;

        database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference().child(FirebaseAuth.getInstance().getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nome = snapshot.child("nome").getValue(String.class);
                textOla.setText("Ola "+ nome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference2;
        PetAdapter petAdapter;
        ArrayList<PetModel> lista;

        reference2 = database.getReference().child(FirebaseAuth.getInstance().getUid()).child("pet");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lista = new ArrayList<>();
        petAdapter = new PetAdapter(this,lista);
        recyclerView.setAdapter(petAdapter);

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for ( DataSnapshot dataSnapshot: snapshot.getChildren()){
                    PetModel petModel = dataSnapshot.getValue(PetModel.class);
                    lista.add(petModel);

                }

                petAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void iniciarComponentes() {
        menuButton = findViewById(R.id.buttonUserTelaPrincipal);
        cadastrarPetButton = findViewById(R.id.buttonCadastrarPet);
        textOla = findViewById(R.id.textOla);
        recyclerView = findViewById(R.id.petList);

    }
}