package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaiontpv.Objetos.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class menu_productos extends AppCompatActivity implements View.OnClickListener {
    private GridView gvArticulos;
    private Button btAceptar;
    private TextView tvInformacion,textView10;
    ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    ArrayList<String> comandaStrings = new ArrayList<>();

    private ListView lvComanda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_productos);
        btAceptar= findViewById(R.id.btAceptarMP);
        btAceptar.setOnClickListener(this);
        gvArticulos= findViewById(R.id.gvArticulos);
        tvInformacion=findViewById(R.id.tvInformacion);
        textView10=findViewById(R.id.textView10);
        Bundle bundle = getIntent().getExtras();
        lvComanda = findViewById(R.id.lvComanda);
        String modo=bundle.getString("modo");
        iniciarProductos(modo);
        if(modo.equals("Bebida")){
            tvInformacion.setText("BEBIDAS");
        }else{
            tvInformacion.setText("CARTA");
        }
    }
    public static void mostrarImagenDesdeBytes(byte[] imageBytes, ImageView imageView) {
        if (imageBytes != null && imageView != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(bitmap);
        }
    }
    private void iniciarProductos(String tipo){
        String query="SELECT COD_ARTICULO, NOMBRE, TIPO, STOCK, PRECIO FROM Articulos WHERE TIPO ='" + tipo + "'";
        try{
            Connection Conexion = ConexionSQLServer.conexionBD();
            ResultSet resultSet = Conexion.prepareStatement(query).executeQuery();
            textView10.setText("Fuera");
            while (resultSet.next()) {
                textView10.setText("Dentro");
                textView10.setText("");
                int cod_articulo = resultSet.getInt(1);
                int stock = resultSet.getInt(4);
                String nombre = resultSet.getString(2);
                Double precio = resultSet.getDouble(5);
                byte[] imagen = new byte[10]; //resultSet.getBytes(3);
                Articulo articulo = new Articulo(cod_articulo, stock, tipo, nombre, precio, imagen);
                articulos.add(articulo);
            }

            resultSet.close();
        }catch(SQLException e){
            //textView10.setText(e.getStackTrace().toString());
            e.printStackTrace();
        }
        AdaptadorArticulos adaptador = new AdaptadorArticulos(this);
        gvArticulos.setAdapter(adaptador);
        for(int i = 0; i < Menu_opciones.articulosComanda.size(); i++)
            if(Menu_opciones.articulosComanda.get(i).getStock() > 0 && Menu_opciones.articulosComanda.get(i).getTipo().equals(tipo))
                for(int j = 0; j < Menu_opciones.articulosComanda.get(i).getStock();j++)
                    agregarArticuloAComanda(Menu_opciones.articulosComanda.get(i), true);
    }
    private void cargarSpiner(ArrayList<String> opciones, Spinner s) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        s.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if(v==btAceptar){
            i = new Intent(this, Menu_opciones.class);
            startActivity(i);
            finish();
        }
    }

    class AdaptadorArticulos extends ArrayAdapter<Articulo> {
        AppCompatActivity appCompatActivity;

        public AdaptadorArticulos(AppCompatActivity context) {
            super(context, R.layout.objeto_articulo, articulos);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.objeto_articulo, null);

            TextView id = item.findViewById(R.id.textView3);
            id.setText(String.valueOf(articulos.get(position).getCod_articulo()));

            TextView textView = item.findViewById(R.id.textView);
            textView.setTextSize(24);
            textView.setText(articulos.get(position).getNombre());

            // ImageView foto = item.findViewById(R.id.imageView);
            // mostrarImagenDesdeBytes(articulos.get(position).getImagen(),foto);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Display a Toast with the ID when the item is clicked
                    Articulo selectedArticulo = articulos.get(position);
                    if(agregarArticuloAComanda(selectedArticulo,false))
                        item.setVisibility(View.GONE);
                }
            });

            return item;
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
                int index = Menu_opciones.FindIndex(articulo.getCod_articulo());

                if (parts.length > 1 && index != -1 && index < articulos.size() && articulos.get(index).getStock() > quantity) {
                    quantity = Integer.parseInt(parts[1].trim()) + 1;
                }
                if (index != -1 && index < articulos.size() && articulos.get(index).getStock() <= quantity) {
                    stock0 = true;
                }

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
            byte[] temp = new byte[10];
            Articulo arti = new Articulo(articulo.getCod_articulo(),1,articulo.getTipo(),articulo.getNombre(),articulo.getPrecio(),temp);
            if(!start)
                Menu_opciones.articulosComanda.add(arti);
        }

        ArrayAdapter<String> comandaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comandaStrings);
        lvComanda.setAdapter(comandaAdapter);
        comandaAdapter.notifyDataSetChanged();
        lvComanda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = comandaStrings.get(position);
                String[] parts = itemClicked.split(" x ");
                String itemName = parts[0];
                String quantityStr = parts[1];
                int quantity = 0;
                int index = comandaStrings.indexOf(itemClicked);

                if (index != -1) {
                    actualizarPrecio(-itemPrice);
                    quantity = Integer.parseInt(quantityStr);
                    int indexA = Menu_opciones.FindIndex(articulo.getCod_articulo());
                    if (quantity > 1) {
                        quantity--;
                        String newItemString = itemName + " x " + quantity;
                        comandaStrings.set(index, newItemString);
                        Menu_opciones.articulosComanda.get(indexA).setStock(quantity);
                    } else {
                        Menu_opciones.articulosComanda.remove(indexA);
                        comandaStrings.remove(index);
                    }
                }
                comandaAdapter.notifyDataSetChanged();
            }
        });
        return stock0;
    }
    public void actualizarPrecio(double precio) {
        String currentText = textView10.getText().toString().trim();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Validate if currentText is a valid numeric string
        if (!currentText.isEmpty() && !currentText.equalsIgnoreCase("TextView")) {
            try {
                double precioTotal = Double.parseDouble(currentText.replace("$", "").replace(",","."));
                precioTotal += precio;

                String formattedPrecioTotal = decimalFormat.format(precioTotal);

                textView10.setText(formattedPrecioTotal + "$");
            } catch (NumberFormatException e) {
                // Handle the case when parsing fails
                e.printStackTrace(); // Log the exception for debugging
            }
        } else {
            // Handle the case when the text is empty or "TextView"
            String formattedPrecio = decimalFormat.format(precio);
            textView10.setText(formattedPrecio + "$");
        }
    }

}