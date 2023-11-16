package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aplicaiontpv.Objetos.Articulo;

import java.util.ArrayList;

public class Menu_opciones extends AppCompatActivity {
    private Button btLogOut,btTerminar,btChat;
    private ImageButton ibBebidas,ibMenu,ibCarta;
    public static ArrayList<Articulo> articulosComanda = new ArrayList<Articulo>();

    public static int FindIndex(long Codigo){
        for(int i = 0; i< articulosComanda.size();i++)
            if(articulosComanda.get(i).getCod_articulo() == Codigo)
                return i;
        return -1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        btLogOut=findViewById(R.id.btLogout);
        btTerminar=findViewById(R.id.btTerminar);
        btChat = findViewById(R.id.btChat);
        ibBebidas=findViewById(R.id.ibBebidas);
        ibCarta=findViewById(R.id.ibCarta);
        ibMenu=findViewById(R.id.ibMenu);
    }
    public void onClick(View v) {
        Intent i;
        if (v.equals(btLogOut)) {
            i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else if (v.equals(btTerminar)) {
            i = new Intent(this, menu_terminar.class);
            startActivity(i);
            finish();
        } else if (v.equals(ibBebidas)) {
            i = new Intent(this, menu_productos.class);
            i.putExtra("modo", "Bebida");
            startActivity(i);
            finish();
        }else if (v.equals(ibCarta)) {
            i = new Intent(this, menu_productos.class);
            i.putExtra("modo", "Carta");
            startActivity(i);
            finish();
        } else if (v.equals(ibMenu)) {
            i = new Intent(this, menu_menu.class);
            startActivity(i);
            finish();
        } else if (v.equals(btChat)) {
            i = new Intent(this, Croqueta.class);
            startActivity(i);
            finish();
        }
    }
}