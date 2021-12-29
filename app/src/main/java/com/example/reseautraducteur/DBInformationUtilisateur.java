package com.example.reseautraducteur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBInformationUtilisateur extends SQLiteOpenHelper {

    public static  DBInformationUtilisateur sqLiteManager;
    public static final String DATABASE_NAME="utilisateur.db";
    public static final int DATABASE_VIERSION=1;
    public static final String TableName ="utilisateur_table";
    public static final String id="id";
    public static final String colomn1="CIN";
    public static final String colomn2="Prenom";
    public static final String colomn3="Nom";
    public static final String colomn4="MotDePasse";
    public static final String colomn5="Adresse";
    public static final String colomn6="mail";
    public static final String colomn7="Telephone";
    public static final String colomn8="Datanaissanse";
    public static final String colomn9="Role";

    public DBInformationUtilisateur(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VIERSION);
    }


    public static DBInformationUtilisateur instanceofDatabase(Context context)
    {
        if(sqLiteManager ==null)
            sqLiteManager=new DBInformationUtilisateur(context);
        return sqLiteManager;
    }

    @Override
    // pour la creation de table
    public void onCreate( SQLiteDatabase db)
    {
        db.execSQL("create TABLE " +TableName+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,CIN INTEGER,Nom TEXT,Prenom TEXT,MotDePasse TEXT,Adresse TEXT,mail TEXT,Telephone INTEGER,Datanaissanse TEXT,Role TEXT)");

    }

    @Override
    //pour la mise à jours
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS "+TableName);
        onCreate(db);

    }

    //insertion des données....................

    public boolean insert( String CIN, String Prenom, String Nom,String MotDePasse,String Adresse,String mail,String Telephone,String Datanaissanse,String Role)
    {   SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(colomn1,CIN);
        contentValues.put(colomn2,Prenom);
        contentValues.put(colomn3,Nom);
        contentValues.put(colomn4,MotDePasse);
        contentValues.put(colomn5,Adresse);
        contentValues.put(colomn6,mail);
        contentValues.put(colomn7,Telephone);
        contentValues.put(colomn8,Datanaissanse);
        contentValues.put(colomn9,Role);
        long l=db.insert(TableName,null,contentValues);
        return l != -1;
    }
    //pour afficher les données
    public Cursor getall()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("select * from "+TableName,null);

    }
    //supprimer les données
    public void delete(String nom)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TableName, "Nom=?", new String[]{nom});
    }
    //checkmail
    public boolean checkmail(String mail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select mail from "+TableName+" where mail=?",new String[]{mail});
        return cursor.getCount() > 0;
    }
    //checkusernamepassword
    public boolean checkmailpassword(String mail,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select mail and MotDePasse from "+TableName+" where mail=? and MotDePasse=?",new String[]{mail,password});
        return cursor.getCount() > 0;
    }
    //getpassword
    public Cursor getMotDepasse(String mail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("Select MotDePasse from "+TableName+" where mail=?",new String[]{mail});
    }
    //getMail
    public Cursor getusername(String nom)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("Select NOM from "+TableName+" where Nom=?",new String[]{nom});
    }
    //getAdresse
    public Cursor getAdresse(String nom)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery(" Select Adresse from "+TableName+" where Nom=?",new String[]{nom});
    }

    //getTelephone
    public Cursor getTelephone(String nom)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("Select Telephone from "+TableName+" where Nom=?",new String[]{nom});
    }
    //getRole
    public Cursor getRole1(String nom)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("Select Role from "+TableName+" where Nom=?",new String[]{nom});
    }
    //getCin

    public Cursor getCIN(String nom)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("Select CIN from "+TableName+" where Nom=?",new String[]{nom});
    }
    //getDatenaissance
    public Cursor getDate(String nom)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("Select Datanaissanse from "+TableName+" where Nom=?",new String[]{nom});
    }

    public static String getColomn9() {
        return colomn9;
    }

    //la liste des Traducteur
    public void populateUtilisateurListArray() {
        SQLiteDatabase db=this.getReadableDatabase();
      try (Cursor result= db.rawQuery("Select * from "+TableName+" Where Role= 'traducteur'",null)){
          if (result.getCount()!=0){
              while (result.moveToNext())
              {
                  int id= result.getInt(1);
                  String nom= result.getString(2);
                  String prenom= result.getString(3);
                  String email=result.getString(4);
                  traducteur traducteur= new traducteur(id,nom,prenom,email);
              }
          }
      }


    }
}
