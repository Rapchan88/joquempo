package com.rapchan.joquempo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    Button btJogar;
    EditText edtNome;
    TextView recorde1;
    TextView recorde2;
    TextView recorde3;
    TextView nome1;
    TextView nome2;
    TextView nome3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        recorde1 = findViewById(R.id.txtRec1);
        recorde2 = findViewById(R.id.txtRec2);
        recorde3 = findViewById(R.id.txtRec3);
        nome1 = findViewById(R.id.txtNome1);
        nome2 = findViewById(R.id.txtNome2);
        nome3 = findViewById(R.id.txtNome3);
        edtNome = findViewById(R.id.edtNome);
        btJogar = findViewById(R.id.btJogar);



                btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference recordes = databaseReference.child("recordes");
                Recorde recorde = new Recorde();


                recorde.setNome(edtNome.getText().toString());
                recorde.setUuid(UUID.randomUUID().toString());
                recordes.child(recorde.getUuid()).setValue(recorde);

                //recordes.setValue(recorde);

                Intent intent = new Intent(getApplicationContext(), JogoActivity.class);
                startActivity(intent);

            }
        });


    }


    }

