package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aplicaiontpv.Objetos.Articulo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class menu_terminar extends AppCompatActivity {

    private TextView textView11;
    private EditText editTextText;

    ArrayList<String> comandaStrings = new ArrayList<>();

    private ListView lvComanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_terminar);
        textView11=findViewById(R.id.textView11);
        editTextText = findViewById(R.id.editTextTextMultiLine);
        lvComanda = findViewById(R.id.lvComanda);
        editTextText.setText(menu_menu.Notas);

        for(int i = 0; i < Menu_opciones.articulosComanda.size(); i++)
            if(Menu_opciones.articulosComanda.get(i).getStock() > 0)
                for(int j = 0; j < Menu_opciones.articulosComanda.get(i).getStock();j++)
                    agregarArticuloAComanda(Menu_opciones.articulosComanda.get(i), true);
    }

    public void Aceptar(View v){
        String query="SELECT MAX(COD_COMANDA) FROM COMANDAS";

        try {
            Connection Conexion = ConexionSQLServer.conexionBD();
            ResultSet resultSet = Conexion.prepareStatement(query).executeQuery();
            int cod_comanda = 0;
            while (resultSet.next()) {
                cod_comanda = resultSet.getInt(1) + 1;
            }
            insertarComandaLocal(cod_comanda, editTextText.getText().toString().trim());
            String query2="INSERT INTO COMANDAS (COD_COMANDA, DNI, NOTAS) VALUES ('" + cod_comanda + "', '" + MainActivity.dni + "', '" + editTextText.getText() + "')";
            Conexion.prepareStatement(query2).executeUpdate();
            for(int i = 0; i<Menu_opciones.articulosComanda.size();i++){
                if(Menu_opciones.articulosComanda.get(i).getTipo() != "Menu"){
                    String query3="INSERT INTO ARTICULOS_COMANDAS (COD_ARTICULO, COD_COMANDA, CANTIDAD) VALUES ('" + Menu_opciones.articulosComanda.get(i).getCod_articulo() + "', '" + cod_comanda + "', '" + Menu_opciones.articulosComanda.get(i).getStock() + "')";
                    Conexion.prepareStatement(query3).executeUpdate();
                    String query4="UPDATE Articulos SET STOCK = STOCK - " + Menu_opciones.articulosComanda.get(i).getStock() + " WHERE COD_ARTICULO = '" + Menu_opciones.articulosComanda.get(i).getCod_articulo() + "'";
                    Conexion.prepareStatement(query4).executeUpdate();
                }
            }
            Menu_opciones.articulosComanda.clear();
            Intent i;
            i = new Intent(this, Menu_opciones.class);
            startActivity(i);
            finish();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void Volver(View v){
        Intent i;
        i = new Intent(this, Menu_opciones.class);
        startActivity(i);
        finish();
    }
    private boolean agregarArticuloAComanda(Articulo articulo, boolean start) {
        String itemName = articulo.getNombre();
        double itemPrice = articulo.getPrecio();
        String itemString ="";
        boolean itemExists = false;
        boolean stock0 = false;

        for (int i = 0; i < comandaStrings.size(); i++) {
            String existingItem = comandaStrings.get(i);

            if (existingItem.contains(itemName)) {
                actualizarPrecio(itemPrice);
                String[] parts = existingItem.split(" x ");
                int quantity = 2;

                if (parts.length > 1 && articulo.getStock() > quantity) {
                    quantity = Integer.parseInt(parts[1].trim()) + 1;
                }
                else if(articulo.getStock() == quantity)
                    stock0 = true;

                itemString = itemName + " x " + quantity;
                comandaStrings.set(i, itemString);
                itemExists = true;
                if(Menu_opciones.FindIndex(articulo.getCod_articulo())!= -1 && !start)
                    Menu_opciones.articulosComanda.get(Menu_opciones.FindIndex(articulo.getCod_articulo())).setStock(quantity);
                break;
            }
        }

        if (!itemExists) {
            actualizarPrecio(itemPrice);
            comandaStrings.add(itemName);
            if(!start)
                Menu_opciones.articulosComanda.add(articulo);
        }
        ArrayAdapter<String> comandaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comandaStrings);
        lvComanda.setAdapter(comandaAdapter);
        comandaAdapter.notifyDataSetChanged();
        return stock0;
    }
    public void actualizarPrecio(double precio) {
        String currentText = textView11.getText().toString().trim();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (!currentText.isEmpty()) {
            double precioTotal = Double.parseDouble(currentText.replace("$", "").replace(",","."));
            precioTotal += precio;

            String formattedPrecioTotal = decimalFormat.format(precioTotal);

            textView11.setText(formattedPrecioTotal + "$");
        } else {
            // Handle the case when the text is empty, e.g., initialize precioTotal to precio
            String formattedPrecio = decimalFormat.format(precio);
            textView11.setText(formattedPrecio + "$");
        }
    }
    private void insertarComandaLocal(int codComanda, String notas) {
        AdminSQLiteOpenHelper dbHelper = new AdminSQLiteOpenHelper(this, "ComandasDB", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("COD_COMANDA", codComanda);
        values.put("DNI", MainActivity.dni);
        values.put("NOTAS", notas);

        // Insertar la comanda en la base de datos local
        db.insert("COMANDAS_LOCAL", null, values);

        // Insertar los detalles de la comanda en la base de datos local
        for (Articulo articulo : Menu_opciones.articulosComanda) {
            if (articulo.getTipo() != "Menu") {
                ContentValues detallesValues = new ContentValues();
                detallesValues.put("COD_ARTICULO", articulo.getCod_articulo());
                detallesValues.put("COD_COMANDA", codComanda);
                detallesValues.put("CANTIDAD", articulo.getStock());

                // Insertar detalles en la base de datos local
                db.insert("ARTICULOS_COMANDAS_LOCAL", null, detallesValues);
            }
        }
        db.close();
    }
}