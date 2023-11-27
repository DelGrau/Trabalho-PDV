package com.example.pdv.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pdv.R;

public class MenuActivity extends AppCompatActivity {

    private Button btPedidos;
    private Button btProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btPedidos = findViewById(R.id.btPedidos);
        btProdutos = findViewById(R.id.btProdutos);

        btPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(PedidosActivity.class);
            }
        });

        btProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(ProdutosActivity.class);
            }
        });
    }

    private void abrirActivity(Class<?> activity) {
        Intent intent = new Intent(MenuActivity.this, activity);
        startActivity(intent);
    }
}