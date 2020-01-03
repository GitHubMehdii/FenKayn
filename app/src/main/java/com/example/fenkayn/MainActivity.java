package com.example.fenkayn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {

    TextView register;
    Button loginButton;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);



        // login the user
        Intent intent = new Intent(MainActivity.this, Map.class);
        startActivity(intent);


/*
        Cursor res = mydb.getAll();
        if(res.getCount()==0){
            showMessage("Error ","Nothing Here");

        }else{
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
                buffer.append("ID "+res.getString(0)+"\n");
                buffer.append("Email "+res.getString(1)+"\n");
                buffer.append("FullName "+res.getString(3)+"\n");
                buffer.append("Gender "+res.getString(4)+"\n");
                buffer.append("BirthPlace "+res.getString(5)+"\n");
            }
            showMessage("Data",buffer.toString());
        }
*/


        // login clicked
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });




        // loginButton clicked
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                // get data
                String email = ((EditText)findViewById(R.id.loginEmail)).getText().toString().trim();
                String password = ((EditText)findViewById(R.id.loginPassword)).getText().toString().trim();

                // check user in DB
                boolean isHere =mydb.checkUser(email, password);

                if (isHere == true){
                    Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_LONG).show();

                    // login the user
                    Intent intent = new Intent(MainActivity.this, Chooser.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this,"Data invalid",Toast.LENGTH_LONG).show();
                }
            }
        });
    }




    public void showMessage(String Title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }
}
