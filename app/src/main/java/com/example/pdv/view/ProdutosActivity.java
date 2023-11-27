package com.example.pdv.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pdv.R;
import com.example.pdv.adapter.ProdutosListAdapter;
import com.example.pdv.controller.ProdutosController;
import com.example.pdv.model.Produtos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProdutosActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroProdutos;
    private RecyclerView rvProdutos;

    // controller
    private ProdutosController controller;

    // Para o cadastro de produtos
    private EditText edDescProd;
    private EditText edValorUnit;

    private View viewAlert;

    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        controller = new ProdutosController(this);
        btCadastroProdutos = findViewById(R.id.btCadastroProdutos);
        rvProdutos = findViewById(R.id.rvProdutos);

        btCadastroProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaProdutos();
    }

    private void abrirCadastro() {
        viewAlert = getLayoutInflater()
                .inflate(R.layout.dialog_cadastro_produtos, null);

        edDescProd = viewAlert.findViewById(R.id.edDescCadastroProduto);
        edValorUnit = viewAlert.findViewById(R.id.edValorUnitCadastroProduto);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO  DE PRODUTOS");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Salvar", null);

        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                    }
                });
            }
        });
        dialog.show();
    }

    public void salvarDados () {
        String retorno = controller.salvarProduto(
                edDescProd.getText().toString(),
                edValorUnit.getText().toString());

        // faz o campo com erro ficar higlighted
        if (retorno != null) {
            if (retorno.contains("DESCRIÇÃO")) {
                edDescProd.setError(retorno);
                edDescProd.requestFocus();
            }

            if (retorno.contains("VALOR")) {
                edValorUnit.setError(retorno);
                edValorUnit.requestFocus();
            }

        } else {
            Toast.makeText(this, "Produto salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();

            atualizarListaProdutos();
        }
    }

    private void atualizarListaProdutos() {
        ArrayList<Produtos> listaProdutos = controller.retornarTodosProdutos();
        ProdutosListAdapter adapter = new ProdutosListAdapter(listaProdutos, this);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setAdapter(adapter);
    }
}