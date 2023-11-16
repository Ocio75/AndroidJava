package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.aplicaiontpv.Objetos.Articulo;

public class menu_menu extends AppCompatActivity {


    public static String Notas = "";
    public RadioGroup rgPrimero,rgSegundo,rgPostre ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_menu);

        rgPrimero=findViewById(R.id.rgPrimero);
        rgSegundo=findViewById(R.id.rgSegundo);
        rgPostre=findViewById(R.id.rgPostre);
    }

    public void atras(View v){
        Intent i;
        i = new Intent(this, Menu_opciones.class);
        startActivity(i);
        finish();
    }

    public void aceptar(View v) {
        Intent i = new Intent(this, Menu_opciones.class);
        RadioButton radioButton1 = findViewById(rgPrimero.getCheckedRadioButtonId());
        RadioButton radioButton2 = findViewById(rgSegundo.getCheckedRadioButtonId());
        RadioButton radioButton3 = findViewById(rgPostre.getCheckedRadioButtonId());
        String Menu = "Menu:  \n" +
                "Primer plato - " + (radioButton1 != null ? radioButton1.getText().toString() : "No seleccionado") + "\n" +
                "Segundo plato - " + (radioButton2 != null ? radioButton2.getText().toString() : "No seleccionado") + "\n" +
                "Postre - " + (radioButton3 != null ? radioButton3.getText().toString() : "No seleccionado") + "\n";

            Notas += Menu;


        byte[] imagen = new byte[10];
        Articulo articulo = new Articulo(0, 1, "Menu", "Menu", 20d, imagen);
        Menu_opciones.articulosComanda.add(articulo);
        startActivity(i);
        finish();
    }

}