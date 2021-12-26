package com.example.reseautraducteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListeDeTraducteur extends AppCompatActivity {
private ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_de_traducteur);
        getSupportActionBar().setTitle("Liste de Tradcteur");
       initWidgets();
       settraducteurAdapteur();
       loadFromDB();

    }

    private void settraducteurAdapteur() {
        traducteurAdapter traducteurAdapter=new traducteurAdapter(getApplicationContext(),traducteur.traducteurArrayList);
    liste.setAdapter(traducteurAdapter);
    }

    private void initWidgets() {
        liste=findViewById(R.id.liste);
    }
    private void loadFromDB() {
        DBInformationUtilisateur dbInformationUtilisateur=DBInformationUtilisateur.instanceofDatabase(this);
        dbInformationUtilisateur.populateUtilisateurListArray();
    }
}