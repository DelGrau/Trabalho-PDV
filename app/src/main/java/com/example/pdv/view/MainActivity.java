package com.example.pdv.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pdv.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abrirView();
    }

    public void abrirView () {
        Intent intent = new Intent (MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}