package com.example.fenkayn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Chooser extends AppCompatActivity {

    Button list10;
    Button list20;
    Button list30;
    Button list40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        initButtons();

    }


    private void initButtons(){
        list10 = findViewById(R.id.list10);
        list10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Chooser.this,"Supermarket",Toast.LENGTH_LONG).show();

                // go to map
                Intent intent = new Intent(Chooser.this, Map.class);
                startActivity(intent);


            }
        });


        list20 = findViewById(R.id.list20);
        list20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Chooser.this,"Medecin",Toast.LENGTH_LONG).show();


                // go to map
                Intent intent = new Intent(Chooser.this, Map.class);
                startActivity(intent);

            }
        });


        list30 = findViewById(R.id.list30);
        list30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Chooser.this,"Station",Toast.LENGTH_LONG).show();


                // go to map
                Intent intent = new Intent(Chooser.this, Map.class);
                startActivity(intent);

            }
        });


        list40 = findViewById(R.id.list40);
        list40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Chooser.this,"Banque",Toast.LENGTH_LONG).show();


                // go to map
                Intent intent = new Intent(Chooser.this, Map.class);
                startActivity(intent);

            }
        });



    }
}
