package com.rapchan.joquempo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class JogoActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    TextView derrotas;
    TextView vitorias;
    TextView empates;
    TextView creditos;
    int derrotasN;
    int vitoriasN;
    int empatesN;
    int creditosN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_jogo);

        derrotasN =1;
        vitoriasN =1;
        empatesN =1;
        creditosN = 9;
        creditos = findViewById(R.id.txtCredito);

    }

    public void selecionarPedra (View view){
        this.opcaoSelecionada("pedra");
    }

    public void selecionarPapel (View view){
        this.opcaoSelecionada("papel");
    }

    public void selecionarTesoura (View view){
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String escolhaUsuario){
        ImageView imgResultado = findViewById(R.id.imgResultado);
        TextView txtResultado = findViewById(R.id.txtResultado);
        String[] opcoes = {"pedra","papel","tesoura"};
        int numero = new Random().nextInt(3);
        String escolhaApp = opcoes[numero];

        derrotas = findViewById(R.id.txtD);
        vitorias = findViewById(R.id.txtV);
        empates = findViewById(R.id.txtE);

        switch (escolhaApp){
            case "pedra":
                imgResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imgResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imgResultado.setImageResource(R.drawable.tesoura);
                break;
        }
        if ((escolhaApp== "pedra"&& escolhaUsuario== "tesoura")||
                (escolhaApp=="papel" && escolhaUsuario=="pedra")||
                (escolhaApp=="tesoura"&& escolhaUsuario=="papel")){
            derrotas.setText((derrotasN++)+"");
            creditos.setText((creditosN--)+"");

            txtResultado.setText("Você perdeu :( " );

            if (creditosN == -1){
                //int rec = vitoriasN-10;


                DatabaseReference recordes = databaseReference.child("recordes");
                Recorde recorde = new Recorde();
                recorde.setPontuacao(vitoriasN);
                recordes.child(recorde.getUuid()).setValue(recorde);


                AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
                msgBox.setTitle("Game Over");
                msgBox.setIcon(R.drawable.ic_mood_bad_black_24dp);
                msgBox.setMessage("Jogar novamente? ");
                msgBox.setCancelable(false);

                msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent = new Intent(getApplicationContext(),JogoActivity.class);
                        startActivity(intent);
                        Toast.makeText(JogoActivity.this, "Boa Sorte", Toast.LENGTH_SHORT).show();
                    }
                });
                msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }

                });msgBox.show();

            }


        }else if ((escolhaUsuario== "pedra"&& escolhaApp== "tesoura")||
                (escolhaUsuario=="papel" && escolhaApp=="pedra")||
                (escolhaUsuario=="tesoura"&& escolhaApp=="papel")){
            vitorias.setText((vitoriasN++)+"");
            txtResultado.setText("Você ganhou :) ");



        }else {empates.setText((empatesN++)+"");
            txtResultado.setText("Nós empatamos ;) ");}



    }


}
