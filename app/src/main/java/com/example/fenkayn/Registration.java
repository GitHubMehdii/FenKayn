package com.example.fenkayn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        DatabaseHelper mydb = new DatabaseHelper(this);

        boolean isInserted = mydb.addUser("oussama@gmail.com","123","mehdiC","14/1/120","male");
        if (isInserted = true){
            Toast.makeText(Registration.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Registration.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
        }

        // login clicked
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
