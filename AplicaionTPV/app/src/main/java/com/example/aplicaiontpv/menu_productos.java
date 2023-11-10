package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaiontpv.Objetos.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class menu_productos extends AppCompatActivity {
    private GridView gvArticulos;
    private TextView tvInformacion,textView10;
    ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_productos);
        gvArticulos= findViewById(R.id.gvArticulos);
        tvInformacion=findViewById(R.id.tvInformacion);
        textView10=findViewById(R.id.textView10);
        Bundle bundle = getIntent().getExtras();
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
    }
    private void cargarSpiner(ArrayList<String> opciones, Spinner s) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        s.setAdapter(adapter);
    }
    class AdaptadorArticulos extends ArrayAdapter<Articulo> {
        AppCompatActivity appCompatActivity;
        public AdaptadorArticulos( AppCompatActivity context) {
            super(context, R.layout.objeto_articulo,articulos);
            appCompatActivity=context;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.objeto_articulo, null);

            TextView id = item.findViewById(R.id.textView3);
            id.setText(articulos.get(position).getCod_articulo());
            TextView textView = item.findViewById(R.id.textView);
            textView.setText(articulos.get(position).getNombre());
          /*  ImageView foto = item.findViewById(R.id.imageView);
            mostrarImagenDesdeBytes(articulos.get(position).getImagen(),foto);*/
        return (item);
        }
    }
}