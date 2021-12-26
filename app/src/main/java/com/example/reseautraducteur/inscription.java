package com.example.reseautraducteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class inscription extends AppCompatActivity {


    //déclaration des variables
    //**********************************************************************************************
    TextInputEditText username,lastname,cin,password,mail,adress,telephone,dateNaissance;
    RadioGroup role;
    RadioButton radioButton;
    Button signup,aleardyhadaccount;
    DBInformationUtilisateur dbInformationUtilisateur;
    //**********************************************************************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
         getSupportActionBar().setTitle("page d'inscription");
        //initialisation des variables
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        lastname=findViewById(R.id.lastname);
       cin=findViewById(R.id.cin);
       adress=findViewById(R.id.adresse);
        telephone=findViewById(R.id.telephone);
        mail=findViewById(R.id.mail);
        dateNaissance=findViewById(R.id.date);
        role=findViewById(R.id.role);
        signup=findViewById(R.id.sign_up_button);
        aleardyhadaccount=findViewById(R.id.button_aleardyhadaccount);
        dbInformationUtilisateur=new DBInformationUtilisateur(this);
        //setOnClickListener pour le button vous avez déja un compte
        //******************************************************************************************
        aleardyhadaccount.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
        });
        //******************************************************************************************

        //setOnClickListener pour le button Création de compte
        //******************************************************************************************
        signup.setOnClickListener(v -> {
            //test pour vérifier que tous les champs sont remplis
            if (username.getText().toString().isEmpty()) {
                username.setError("Nom  obligatoire !");
                username.requestFocus();
            } else if (lastname.getText().toString().isEmpty()) {
                password.setError("Prenom obligatoire!");
                password.requestFocus();
            } else if (password.getText().toString().isEmpty()) {
                password.setError("Mot de passe obligatoire!");
                password.requestFocus();
            }
            //longueur de password doit étre >6
            else if (password.getText().toString().length() < 6) {
                password.setError("Longueur minimal de mot de passe 6!");
                password.requestFocus();
            } else if (adress.getText().toString().isEmpty()) {
                adress.setError("Adresse  obligatoire!");
                adress.requestFocus();
            } else if (mail.getText().toString().isEmpty()) {
                mail.setError("Adresse mail  obligatoire!");
                mail.requestFocus();
            } else if (dateNaissance.getText().toString().isEmpty()) {
                mail.setError("date   obligatoire!");
                mail.requestFocus();
            } else if (telephone.getText().toString().isEmpty()) {
                telephone.setError("Téléphone obligatoire!");
                telephone.requestFocus();
            } else if (telephone.getText().toString().length() < 8) {
                telephone.setError("Téléphone  non valide!");
                telephone.requestFocus();
            } else {
                boolean checkuser = dbInformationUtilisateur.checkmail(mail.getText().toString());
                if (!checkuser) {
                    int selectedId = role.getCheckedRadioButtonId();
                    radioButton = findViewById(selectedId);
                    boolean insert = dbInformationUtilisateur.insert(cin.getText().toString(), lastname.getText().toString(), username.getText().toString(), password.getText().toString(), adress.getText().toString(), mail.getText().toString(), telephone.getText().toString(), dateNaissance.getText().toString(), radioButton.getText().toString());
                   if(insert){
                    Toast.makeText(getApplicationContext(), "inscription réussie", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "échec de l'enregistrement", Toast.LENGTH_LONG).show();
                }
            }
                else
            {
                Toast.makeText(getApplicationContext(), "utilisateur existe déja! ", Toast.LENGTH_LONG).show();
            }
        }

        });
        //******************************************************************************************
    }
}