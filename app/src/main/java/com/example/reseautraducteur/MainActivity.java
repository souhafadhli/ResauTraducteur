package com.example.reseautraducteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //déclaration des variables
    //**********************************************************************************************
    Button start;
    //**********************************************************************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialisation de Button start
        //******************************************************************************************
        start=findViewById(R.id.bt_start);
        //******************************************************************************************


        //méthode setOnclickListener pour le button start
        //******************************************************************************************
        start.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);});
        //******************************************************************************************
    }
}
