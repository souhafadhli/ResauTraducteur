package com.example.reseautraducteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {
    //déclaration des variables
    //**********************************************************************************************
    TextView creataccount;
    Button se_connecter;
    TextInputEditText mail,password;
    DBInformationUtilisateur dbInformationUtilisateur;
    TextView forgotpassword;
    //**********************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("page d'authentification");
        //initialisation des variables
        //******************************************************************************************
        creataccount=findViewById(R.id.creataccount);
        mail=findViewById(R.id.mail);
        password=findViewById(R.id.password);
        forgotpassword=findViewById(R.id.forgot);
        dbInformationUtilisateur=new DBInformationUtilisateur(this);
        //******************************************************************************************

        //créer un compte
        //******************************************************************************************
        creataccount.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),inscription.class);
            startActivity(intent);
        });
        //******************************************************************************************

        //button pour se connecter
        //******************************************************************************************
        se_connecter=findViewById(R.id.button_sign_in);
        se_connecter.setOnClickListener(v -> {
            //test pour vérifier  que tous les champs sont remplis
            if(mail.getText().toString().isEmpty())
            {
                mail.setError("mail obligatoire!");
                mail.requestFocus();
            }
            else if(password.getText().toString().isEmpty())
            {
                password.setError("Mot de passe obligatoire");
                password.requestFocus();
            }
            //test pour vérifier que la longueur de password est >6
            else if(password.getText().toString().length()<6)
            {
                password.setError("Longueur minimal de mot de passe 6!");
                password.requestFocus();
            }
            else
            {
                boolean checkuserpass=dbInformationUtilisateur.checkmailpassword(mail.getText().toString(),password.getText().toString());
                //test pour vérifier si l'utilisateur existe ou non
                if(checkuserpass)
                {
                    Toast.makeText(getApplicationContext(),"connexion réussie",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),ListeDeTraducteur.class);
                    intent.putExtra("mail",mail.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"donnée invalide!",Toast.LENGTH_LONG).show();
                }
            }
        });
        //******************************************************************************************

        //mot de passe oublier
        //******************************************************************************************
        forgotpassword.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),MotDePasse.class);
            startActivity(intent);
        });
        //******************************************************************************************
    }
}
