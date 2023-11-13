package com.example.aplicaiontpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogIn=findViewById(R.id.btLogIn);
    }
    public void login(View v){
        Intent i;
        i = new Intent(this, ChatActivity.class);
        startActivity(i);
        finish();
    }
}
