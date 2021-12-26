package com.example.reseautraducteur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class traducteurAdapter extends ArrayAdapter<traducteur> {
    public traducteurAdapter(Context context, List<traducteur> traducteurs)
    {
        super(context,0,traducteurs);
    }
    @NonNull
    @Nullable
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    { traducteur traducteur=getItem(position);
    if(convertView == null)
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.single_item,parent,false);
        TextView id=convertView.findViewById(R.id.item_id);
        TextView nom=convertView.findViewById(R.id.item_name);
        TextView prenom=convertView.findViewById(R.id.item_lastname);
        TextView email=convertView.findViewById(R.id.item_email);

        id.setText(traducteur.getId());
        nom.setText(traducteur.getNom());
        prenom.setText(traducteur.getPrenom());
        email.setText(traducteur.getEmail());
      return convertView;
    }
}
