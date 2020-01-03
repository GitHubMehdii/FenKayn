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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fenkayn.models.User;

import java.util.Calendar;
public class Registration extends AppCompatActivity {

    TextView login;
    Button next;
    DatePickerDialog picker;
    EditText registerBirthDate;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mydb =new DatabaseHelper(this);

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

                EditText registerEmail = (EditText)findViewById(R.id.registerEmail);
                EditText registerPassword = (EditText)findViewById(R.id.registerPassword);
                EditText registerPasswordConfirmation = (EditText)findViewById(R.id.registerPasswordConfirmation);
                EditText registerName = (EditText)findViewById(R.id.registerName);
                EditText registerBirthDate = (EditText)findViewById(R.id.registerBirthDate);
                RadioGroup registerGender = (RadioGroup) findViewById(R.id.registerGender);

                if(!Validator.isValidEmail(registerEmail)){
                    errorMessage += "\n   - Email invalid!";
                    isValid = false;
                    registerEmail.setHintTextColor(getResources().getColor(R.color.colorDanger));
                    registerEmail.setTextColor(getResources().getColor(R.color.colorDanger));
                }
                else{
                    registerEmail.setHintTextColor(getResources().getColor(R.color.colorBlack));
                    registerEmail.setTextColor(getResources().getColor(R.color.colorBlack));
                }

                if(!Validator.isValidLength(registerPassword, 6)){
                    errorMessage += "\n   - Mot de passe invalid (6 caractères)!";
                    isValid = false;
                    registerPassword.setHintTextColor(getResources().getColor(R.color.colorDanger));
                    registerPassword.setTextColor(getResources().getColor(R.color.colorDanger));
                }
                else{
                    registerPassword.setHintTextColor(getResources().getColor(R.color.colorBlack));
                    registerPassword.setTextColor(getResources().getColor(R.color.colorBlack));
                }

                if(!Validator.isPasswordConfirmed(registerPassword,
                        registerPasswordConfirmation)){
                    errorMessage += "\n   - Confirmation invalide!";
                    isValid = false;
                    registerPasswordConfirmation.setHintTextColor(getResources().getColor(R.color.colorDanger));
                    registerPasswordConfirmation.setTextColor(getResources().getColor(R.color.colorDanger));
                }
                else{
                    registerPasswordConfirmation.setHintTextColor(getResources().getColor(R.color.colorBlack));
                    registerPasswordConfirmation.setTextColor(getResources().getColor(R.color.colorBlack));
                }

                if(Validator.isEmpty(registerName)){
                    errorMessage += "\n   - Nom invalid!";
                    isValid = false;
                    registerName.setHintTextColor(getResources().getColor(R.color.colorDanger));
                    registerName.setTextColor(getResources().getColor(R.color.colorDanger));
                }
                else{
                    registerName.setHintTextColor(getResources().getColor(R.color.colorBlack));
                    registerName.setTextColor(getResources().getColor(R.color.colorBlack));
                }

                if(!Validator.isValidBirthDate(registerBirthDate)){
                    errorMessage += "\n   - Date de naissance invalide!";
                    isValid = false;
                    registerBirthDate.setHintTextColor(getResources().getColor(R.color.colorDanger));
                    registerBirthDate.setTextColor(getResources().getColor(R.color.colorDanger));
                }
                else{
                    registerBirthDate.setHintTextColor(getResources().getColor(R.color.colorBlack));
                    registerBirthDate.setTextColor(getResources().getColor(R.color.colorBlack));
                }

                if(!Validator.isCheckedRadioGroup(registerGender)){
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
                else {
                    // set user
                    User user = new User();
                    user.setEmail(registerEmail.getText().toString().trim());
                    user.setPassword(registerPassword.getText().toString().trim());
                    user.setName(registerName.getText().toString().trim());
                    user.setBirthDate(registerBirthDate.getText().toString().trim());

                    int checkedId = registerGender.getCheckedRadioButtonId();
                    RadioButton checkedRadio = (RadioButton) findViewById(checkedId);
                    String gender = checkedRadio.getText().toString().trim().equals("Homme") ?
                            "male" : "female";
                    user.setGender(gender);

                    System.out.println(user);

                    // add user
                    boolean alreadyRegister = mydb.checkUserByEmail(user.getEmail());
                    if (alreadyRegister == false){
                        mydb.addUser(user.getEmail(), user.getPassword(), user.getName(), user.getBirthDate(), user.getGender());
                        // code here redirection

                    }else if(alreadyRegister == true){
                        new AlertDialog.Builder(Registration.this)
                                .setTitle("Utilisateur déja enregistré")
                                .setPositiveButton(android.R.string.yes, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                }

            }
        });

    }

}

