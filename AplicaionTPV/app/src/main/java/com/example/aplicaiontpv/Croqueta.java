package com.example.aplicaiontpv;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Croqueta extends AppCompatActivity implements View.OnClickListener {
    private EditText messageEditText;
    private LinearLayout chatLayout;
    private Button sendButton, btSalir;

    private Socket socket;
    private PrintWriter output;

    private final Executor executor = Executors.newSingleThreadExecutor();
    private String lastSentMessage = ""; // Almacena el último mensaje enviado
    private String usuario; // Almacena el nombre de usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_croqueta);
        btSalir= findViewById(R.id.sendButton2);
        btSalir.setOnClickListener(this);
        messageEditText = findViewById(R.id.messageEditText);
        chatLayout = findViewById(R.id.chatLayout);
        sendButton = findViewById(R.id.sendButton);

        Bundle extras = getIntent().getExtras();
        usuario = String.valueOf(MainActivity.dni);

        new Thread(() -> {
            try {
                Log.d("ClientAsyncTask", "Intentando conectar al servidor...");
                //socket = new Socket("10.0.2.2", 12345);
                socket = new Socket("192.168.0.5", 12345);
                Log.d("ClientAsyncTask", "Conectado al servidor.");

                output = new PrintWriter(socket.getOutputStream(), true);

                // Envía el nombre de usuario al servidor al establecer la conexión
                output.println(usuario);

                Scanner input = new Scanner(socket.getInputStream());

                while (true) {
                    if (input.hasNext()) {
                        String message = input.nextLine();
                        Log.d("ClientAsyncTask", "Mensaje recibido: " + message);

                        // Verifica si el mensaje recibido es diferente al último mensaje enviado
                        if (!message.equals(lastSentMessage)) {
                            runOnUiThread(() -> {
                                displayMessage(message, false);
                            });
                        }
                    }
                }
            } catch (IOException e) {
                Log.e("ClientAsyncTask", "Error de conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();

        sendButton.setOnClickListener(view -> {
            sendMessage();
        });
    }

    private void sendMessage() {
        Log.d("ChatActivity", "Enviando mensaje...");
        String message = messageEditText.getText().toString();
        if (!message.isEmpty() && output != null) {
            executor.execute(() -> {
                try {
                    Log.d("SendMessageAsyncTask", "Conectando al servidor");
                    output.println( message);
                    Log.d("SendMessageAsyncTask", "Mensaje enviado: " + message);

                    // Actualiza el último mensaje enviado
                    lastSentMessage = usuario + ": " + message;

                    runOnUiThread(() -> {
                        displayMessage(usuario + ": " + message, true);
                    });
                } catch (Exception e) {
                    Log.e("SendMessageAsyncTask", "Error al enviar mensaje: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            messageEditText.setText("");
            Log.d("ChatActivity", "Mensaje enviado correctamente.");
        }
    }

    private void displayMessage(String message, boolean sentByMe) {
        TextView textView = new TextView(this);
        textView.setText(message);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Ajusta la gravedad según si el mensaje fue enviado por el propio cliente
        if (sentByMe) {
            layoutParams.gravity = Gravity.END;
        } else {
            layoutParams.gravity = Gravity.START;
        }

        textView.setLayoutParams(layoutParams);
        chatLayout.addView(textView);
    }

    @Override
    public void onClick(View v) {
        if(v==btSalir){
            Intent i;
            i = new Intent(this, Menu_opciones.class);
            startActivity(i);
            finish();
        }
    }
}