package com.example.reseautraducteur;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MotDePasse extends AppCompatActivity {
    //déclaration des variables
    //**********************************************************************************************
    Button sendpassword;
    EditText email,nom;
    DBInformationUtilisateur dbHelpeur;
    //**********************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mot_de_passe);
        getSupportActionBar().setTitle("Mot De passe oublie");
        //initialisation des variables
        //******************************************************************************************
        sendpassword=findViewById(R.id.bt);
        email=findViewById(R.id.email);
        nom=findViewById(R.id.nom);
        dbHelpeur=new DBInformationUtilisateur(this);
        //******************************************************************************************
        //button sendpassword pour envoyer le mot de passse
        //******************************************************************************************
        sendpassword.setOnClickListener(v -> {
            Cursor cursor=dbHelpeur.getMotDepasse(nom.getText().toString());
            if (cursor.getCount() == 0) {
                Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_SHORT).show();
            } else {
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("MotDepasse: " + cursor.getString(0) + "\n");

                }
                sendEmail(buffer.toString());

            }
        });
    }

    public void sendEmail(String message)
    {
        JavaMailAPi javaMailAPi=new JavaMailAPi(this,email.getText().toString(),"Votre Mot De Passe"
                ,message);
        javaMailAPi.execute();
    }
    //**********************************************************************************************
}