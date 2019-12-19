package com.example.sqllite.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllite.Details;
import com.example.sqllite.R;
import com.example.sqllite.model.Contact;

import java.util.List;




public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Contact> contactList;


    //contructor pide contexto y la lista de contactos
    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    //OnCreate!
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup mainAct, int viewType) {
        View view = LayoutInflater.from(mainAct.getContext()).
                inflate(R.layout.items_editables,
                        mainAct,
                        false
                    );


        return new ViewHolder(view);
    }



    //donde se trabaja la info
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {

        Contact contact = contactList.get(index);//cada contacto dentro de la List
        holder.contactname.setText(contact.getName());
        holder.telefono.setText(contact.getPhone());

    }



    //regresa el conteo de los datos
    @Override
    public int getItemCount() {
        return contactList.size();

    }


    //esta clase mantiene los valores de cada item CardView
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView contactname;
        public TextView telefono;
        public ImageView btn;

        //como le pasamos la vista desde el onCreate se trabaja con ella
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //hacer nuestro item clickleable
            //itemView.setOnClickListener(this);

            contactname = itemView.findViewById(R.id.name);
            telefono = itemView.findViewById(R.id.telefono);
            btn = itemView.findViewById(R.id.imagenView);



            btn.setOnClickListener(this);




        }



        @Override
        public void onClick(View view) {

            //desde la clase RecyclerViewer
            int pos =  getAdapterPosition();
            Contact contact = contactList.get(pos);

            Intent intent = new Intent(context, Details.class);

            intent.putExtra("name",contact.getName());
            intent.putExtra("phone",contact.getPhone());

            //el context tiene los metodos del Main
            context.startActivity(intent);

            //Toast.makeText(context, "desde onClick"+contact.getName(), Toast.LENGTH_LONG).show();
        }
    }


}






















