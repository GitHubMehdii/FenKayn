package com.example.fenkayn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
public class Registration extends AppCompatActivity {

    TextView login;
    Button next;
    DatePickerDialog picker;
    EditText registerBirthDate;

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


        this.initLogin();

        this.onNextClicked();

        this.initBirthDatePicker();
    }

    private void initBirthDatePicker(){
        registerBirthDate =(EditText) findViewById(R.id.registerBirthDate);
        registerBirthDate.setInputType(InputType.TYPE_NULL);
        registerBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Registration.this,
                        R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                registerBirthDate.setText(
                                        ((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth) + "/"
                                                + (((monthOfYear + 1) < 10) ? "0" + (monthOfYear + 1) : (monthOfYear + 1)) + "/"
                                                + year
                                );
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }


    private void initLogin(){
        // login clickListener
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    private void onNextClicked(){
        // next clickListener
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validation

                String errorMessage = "Veuillez vérifier votre coordonnées!";
                boolean isValid = true;

                if(!Validator.isValidEmail((EditText)findViewById(R.id.registerEmail))){
                    errorMessage += "\n   - Email invalide!";
                    isValid = false;
                }

                if(!Validator.isValidLength((EditText)findViewById(R.id.registerPassword), 6)){
                    errorMessage += "\n   - Mot de passe doit comporter un minimum de 6 caractères!";
                    isValid = false;
                }

                if(!Validator.isPasswordConfirmed((EditText)findViewById(R.id.registerPassword),
                        (EditText)findViewById(R.id.registerPasswordConfirmation))){
                    errorMessage += "\n   - Confirmation invalide!";
                    isValid = false;
                }

                if(Validator.isEmpty((EditText)findViewById(R.id.registerName))){
                    errorMessage += "\n   - Saisissez votre nom!";
                    isValid = false;
                }

                if(!Validator.isValidBirthDate((EditText)findViewById(R.id.registerBirthDate))){
                    errorMessage += "\n   - Date de naissance invalide!";
                    isValid = false;
                }

                if(!Validator.isCheckedRadioGroup(((RadioGroup)findViewById(R.id.registerGender)))){
                    errorMessage += "\n   - Êtes-vous un homme ou une femme!";
                    isValid = false;
                }

                if(!isValid){
                    new AlertDialog.Builder(Registration.this)
                            .setTitle("Coordonnées invalides")
                            .setMessage(errorMessage)
                            .setPositiveButton(android.R.string.yes, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else{
                    // code here
                }

                ((EditText)findViewById(R.id.registerEmail)).setHintTextColor(getResources().getColor(R.color.colorRose));
                ((EditText)findViewById(R.id.registerEmail)).setTextColor(getResources().getColor(R.color.colorRose));
            }
        });

    }

}

