package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btLogIn;
    private EditText etUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogIn = findViewById(R.id.btLogIn);
        etUsuario = findViewById(R.id.etUsuario);
    }

    public void login(View v) {
        // Obtener el valor del campo etUsuario
        String usuario = etUsuario.getText().toString();

        // Pasar el valor a ChatActivity
        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra("USUARIO", usuario); // Usamos "USUARIO" como clave para el valor
        startActivity(i);
        finish();
    }
}
