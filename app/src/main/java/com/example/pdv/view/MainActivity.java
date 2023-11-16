package com.example.pdv.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pdv.R;

public class MainActivity extends AppCompatActivity {

    // Variaveis
    Button btNovoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNovoPedido = findViewById(R.id.btNovoPedido);

        btNovoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirNovoPedido(view);
            }
        });
    }

    public void abrirNovoPedido(View view) {
        Intent intent = new Intent(MainActivity.this, NovoPedidoActivity.class);

        startActivity(intent);
    }
}