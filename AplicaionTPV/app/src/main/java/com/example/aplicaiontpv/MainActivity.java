package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicaiontpv.Objetos.Articulo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btLogIn;
    private EditText etUsuario,etPassword;
    private String contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsuario= findViewById(R.id.etUsuario);
        etPassword=findViewById(R.id.etPassword);
        btLogIn=findViewById(R.id.btLogIn);
        btLogIn.setOnClickListener(this);
    }
    private void hacerLogin(){
        String query="select CONTRASEÑA from Empleados where DNI ="+etUsuario.getText();

        try{
            Connection Conexion = ConexionSQLServer.conexionBD();
            ResultSet resultSet = Conexion.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                contrasena = resultSet.getString(1);
            }

            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(etPassword.getText().toString().equals(contrasena)){
            Toast.makeText(this, "Logea ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, " no Logea ", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View v) {
        if(v==btLogIn){
            hacerLogin();
        }
        /*
        i = new Intent(this, Menu_opciones.class);
        startActivity(i);
        finish();*/
    }
}
