package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class menu_menu extends AppCompatActivity {

    public RadioButton rbPrimero1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_menu);

        rbPrimero1=findViewById(R.id.rbPrimero1);

        rbPrimero1.setHeight(2);
        rbPrimero1.setWidth(2);
    }

    public void atras(View v){
        Intent i;
        i = new Intent(this, Menu_opciones.class);
        startActivity(i);
        finish();
    }

    public void aceptar(View v){
        Intent i;
        i = new Intent(this, Menu_opciones.class);
        startActivity(i);
        finish();
    }

}