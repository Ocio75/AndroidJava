package com.example.aplicaiontpv;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatActivity extends AppCompatActivity {
    private EditText messageEditText;
    private TextView chatTextView;
    private Button sendButton;

    private Socket socket;
    private PrintWriter output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageEditText = findViewById(R.id.messageEditText);
        chatTextView = findViewById(R.id.chatTextView);
        sendButton = findViewById(R.id.sendButton);

        new ClientAsyncTask().execute();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = messageEditText.getText().toString();
        if (!message.isEmpty() && output != null) {
            new SendMessageAsyncTask().execute(message);
            messageEditText.setText("");
        }
    }

    private class ClientAsyncTask extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                socket = new Socket("10.0.2.2", 12345);
                output = new PrintWriter(socket.getOutputStream(), true);
                Scanner input = new Scanner(socket.getInputStream());

                while (true) {
                    if (input.hasNext()) {
                        String message = input.nextLine();
                        publishProgress(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            // Actualizar la interfaz de usuario con el mensaje recibido
            chatTextView.append(values[0] + "\n");
        }
    }

    private class SendMessageAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... messages) {
            output.println(messages[0]);
            return null;
        }
    }
}
