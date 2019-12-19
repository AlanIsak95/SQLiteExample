package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {


    private TextView name,phone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();


        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);


        name.setText(bundle.getString("name"));
        phone.setText(bundle.getString("phone"));


    }
}
