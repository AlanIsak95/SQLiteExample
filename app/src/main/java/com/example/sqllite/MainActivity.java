package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqllite.adapter.RecyclerViewAdapter;
import com.example.sqllite.data.DataBaseHandler;
import com.example.sqllite.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //////IIIIMMMPOOORTAANTEEE///////
    //no saldran registros si no hay nada en la DB, favor de usar db.addContact

   // private ListView listaDondeSeMuestraInfo;//donde se muestra la info LIST
   // private ArrayList <String> arregloQueTieneLaInfo; //la info para LIST
   // private ArrayAdapter <String> adaptadorArrayTOList;//el adaptador de LIST


    //Parecido a un ListView
    private ArrayList <Contact> arregloQueTieneLaInfo; //contenedor de obj Contact
    private RecyclerView recyclerView;//objeto donde se coloca la info
    private RecyclerViewAdapter recyclerViewAdapter;//adaptador creado por nosotros



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //se busca por ID
        recyclerView = findViewById(R.id.Listanueva);
        // listaDondeSeMuestraInfo = findViewById(R.id.ListMostrar); //Para LIST


        //algunos parametros del recycler para LIST
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //inicializamos
        arregloQueTieneLaInfo = new ArrayList<>();
        DataBaseHandler db = new DataBaseHandler(MainActivity.this);

        Contact contac= new Contact("alan","32234");
        Contact contac2= new Contact("erick","234");
        Contact contac3= new Contact("jen","6777");
        Contact contac4= new Contact("hec23","3333");
        Contact contac5= new Contact("xo","22222");




        //obtenemos todos los objetos de contactos de la base de datos
        List <Contact> contactList = db.getall();//regresa una lista



        //se agrega cada item de la LIST contac  al ArrayList de Contact
        for (Contact item: contactList){

           arregloQueTieneLaInfo.add(item);

        }


        //preparamos la informacion para poder agregarla a nuestro RecyclerView pasandole un ArrayList con la info
        recyclerViewAdapter = new RecyclerViewAdapter(this,arregloQueTieneLaInfo);

        //colocamos la informacion procesada en nuestro Recycler
        recyclerView.setAdapter(recyclerViewAdapter);




    }//onCreate()




}
