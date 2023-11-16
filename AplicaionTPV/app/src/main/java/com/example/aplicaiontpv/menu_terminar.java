package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

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

    public static String Notas;

    ArrayList<String> comandaStrings = new ArrayList<>();

    private ListView lvComanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_terminar);
        textView11=findViewById(R.id.textView11);
        editTextText = findViewById(R.id.editTextTextMultiLine);
        lvComanda = findViewById(R.id.lvComanda);
        editTextText.setText(Notas);

        for(int i = 0; i < Menu_opciones.articulosComanda.size(); i++)
            if(Menu_opciones.articulosComanda.get(i).getStock() > 0)
                for(int j = 0; j < Menu_opciones.articulosComanda.get(i).getStock();j++)
                    agregarArticuloAComanda(Menu_opciones.articulosComanda.get(i), true);
    }

    public void Aceptar(View v){
        String query="SELECT MAX(COD_COMANDA) FROM COMANDAS;";

        try {
            Connection Conexion = ConexionSQLServer.conexionBD();
            ResultSet resultSet = Conexion.prepareStatement(query).executeQuery();
            long cod_comanda = resultSet.getLong(1);
            //String query2="INSERT INTO COMANDAS (COD_COMANDA, DNI, NOTAS) VALUES ('" + cod_comanda + 1 + "', '" + Login.DNI + "', '" + editTextText.getText() + "')";
            //Conexion.prepareStatement(query2).executeQuery();
            for(int i = 0; i<Menu_opciones.articulosComanda.size();i++){
                String query3="INSERT INTO ARTICULOS_COMANDAS (COD_ARTICULO, COD_COMANDA, CANTIDAD) VALUES ('" + Menu_opciones.articulosComanda.get(i).getCod_articulo() + "', '" + cod_comanda + "', '" + Menu_opciones.articulosComanda.get(i).getStock() + "')";
                Conexion.prepareStatement(query3).executeQuery();
                String query4="UPDATE Articulos SET STOCK = STOCK - " + Menu_opciones.articulosComanda.get(i).getStock() + " WHERE COD_ARTICULO = '" + Menu_opciones.articulosComanda.get(i).getCod_articulo() + "'";
                Conexion.prepareStatement(query4).executeQuery();
            }
            Menu_opciones.articulosComanda.clear();
        }catch(SQLException e){
            e.printStackTrace();
        }
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
                if(Menu_opciones.FindIndex(articulo.getCod_articulo())!= -1 && start)
                    Menu_opciones.articulosComanda.get(Menu_opciones.FindIndex(articulo.getCod_articulo())).setStock(quantity);
                break;
            }
        }

        if (!itemExists) {
            actualizarPrecio(itemPrice);
            comandaStrings.add(itemName);
            if(start)
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
            double precioTotal = Double.parseDouble(currentText.replace("$", ""));
            precioTotal += precio;

            String formattedPrecioTotal = decimalFormat.format(precioTotal);

            textView11.setText(formattedPrecioTotal + "$");
        } else {
            // Handle the case when the text is empty, e.g., initialize precioTotal to precio
            String formattedPrecio = decimalFormat.format(precio);
            textView11.setText(formattedPrecio + "$");
        }
    }
}