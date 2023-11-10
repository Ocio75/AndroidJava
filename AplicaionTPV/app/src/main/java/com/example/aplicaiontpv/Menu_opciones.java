package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu_opciones extends AppCompatActivity {
    private Button btLogOut,btTerminar;
    private ImageButton ibBebidas,ibMenu,ibCarta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);

        btLogOut=findViewById(R.id.btLogout);
        btTerminar=findViewById(R.id.btTerminar);
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
        }
    }
}