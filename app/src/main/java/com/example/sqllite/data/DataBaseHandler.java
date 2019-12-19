package com.example.sqllite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.ULocale;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.sqllite.R;
import com.example.sqllite.model.Contact;
import com.example.sqllite.util.Util;

import java.util.ArrayList;
import java.util.List;

//hereda de SQLLITE
public class DataBaseHandler extends SQLiteOpenHelper {


    //contructor
    public DataBaseHandler(Context context ) {

        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);

    }


    //CUANDO SE CREA LA CLASE SE EJECUTA ESTE METODO
    @Override
    public void onCreate(SQLiteDatabase db) {


        //SQL CRATE
        String CREATE_CONTACT_TABLE=" CREATE TABLE "+ Util.TABLE_NAME+"("
                +Util.KEY_ID + " INTEGER PRIMARY KEY, "
                +Util.KEY_NAME + " TEXT,"
                +Util.KEY_PHONE_NUMBER + " TEXT"

         +")";


        db.execSQL(CREATE_CONTACT_TABLE);//CREATING
        
    }

    //onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String DROP_TABLE =String.valueOf(R.string.dbDROP);
            db.execSQL(DROP_TABLE,new String [] {Util.DATABASE_NAME});

        //crear tabla de nuevo
            onCreate(db);
    }



//////////////////////////       C R U D      //////////////////////////

    //CREATE
    public void addContacto(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME,contact.getName());
        contentValues.put(Util.KEY_PHONE_NUMBER,contact.getPhone());


        //insert
        db.insert(Util.TABLE_NAME,null,contentValues);
        Log.d("ISAK", "added: "+contentValues.getAsString(Util.KEY_NAME));
        db.close();


    }

    //READ BY ID
    public Contact getContact(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        //trabajar con arreglos
        //cursor necesita las siguientes declaraciones
        Cursor cursor = db.query(Util.TABLE_NAME,//nombre de la tabla
                new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONE_NUMBER},//un array con los datos de los headers
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},//ID =? id(pero debe estar en arreglo)
                null,null,null//pasar nulls
                );


        if (cursor != null) {
            cursor.moveToFirst();
        }

        //Creamos un nuevo objeto con lo sresultados de la consulta

        Contact contactRes =new Contact();
        //el cursor tiene la siguiente forma [1 (pos 0) , Alan (pos 1), 775494949 (pos2)]
        contactRes.setId(Integer.parseInt(cursor.getString(0)));//obtenemos el valor del string
        contactRes.setName(cursor.getString(1));
        contactRes.setPhone(cursor.getString(2));

        //regresamos el contacto
        return  contactRes;

    }


    //READ ALL
    public List <Contact> getall (){

        List <Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //select all contacts
        String selectAll = "SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);



        //Iterar entre todos los registros
        if (cursor.moveToFirst()){

            do {
                Contact contact= new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));

                //añadir el contacto a la lista
                contactList.add(contact);

            }while (cursor.moveToNext());

        }

        //REGRESAR LA LISTA
    return  contactList;
    }

    //UPDATE
    public int updateContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME,contact.getName());
        contentValues.put(Util.KEY_PHONE_NUMBER,contact.getPhone());

        //update the row
        //update Table name, values, where, ID = contact.getid
        int resultado = db.update(Util.TABLE_NAME,contentValues, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())}
        );

        db.close();

        return  resultado;


    }

    //DELETE
    public void deleteContact (Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Delete Table name, donde el ID sea igual al valor de contact.getid pasado como un arreglo
        db.delete(Util.TABLE_NAME,Util.KEY_ID + "=?",
                new String[]{
                        String.valueOf(contact.getId())}
                );

    }


    //Get Contact count
    public int getCount(){

        String CountQuery = "Select * from "+Util.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(CountQuery,null);
        int count= cursor.getCount();
        db.close();

        return count;



    }

////////////////////////// F I N     C R U D //////////////////////////



}
