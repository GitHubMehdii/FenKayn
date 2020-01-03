package com.example.fenkayn;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fenkayn.models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Validator {


    /**
     * Validates isCheckedRadioButton
     *
     * @param radioGroup
     * @return
     */
    public static boolean isCheckedRadioGroup(RadioGroup radioGroup){
        return radioGroup.getCheckedRadioButtonId() != -1;
    }


    /**
     * Validates emptiness
     *
     * @param editText
     * @return
     */
    public static boolean isEmpty(EditText editText){

        String text = editText.getText().toString().trim();

        return 0 == text.length();
    }


    /**
     * Validates emptiness
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){

        String text = string.toString().trim();

        return 0 == text.length();
    }


    /**
     * Validates emptiness
     *
     * @param radioButton
     * @return
     */
    public static boolean isEmpty(RadioButton radioButton){
        String text = radioButton.getText().toString().trim();

        return 0 == text.length();
    }


    /**
     * Validates email
     *
     * @param editText
     * @return
     */
    public static boolean isValidEmail(EditText editText){

        final String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        String text = editText.getText().toString().trim();

        return 0 < text.length() &&  text.matches(pattern);
    }

    /**
     * Validates length of an editText
     *
     * @param editText
     * @param length
     * @return
     */
    public static boolean isValidLength(EditText editText, int length){

        String text = editText.getText().toString().trim();
        return 0 < text.length() && text.length() < length;
    }

    /**
     * Validates password confirmation
     *
     * @param editText1
     * @param editText2
     * @return
     */
    public static boolean isPasswordConfirmed(EditText editText1, EditText editText2){

        String text1 = editText1.getText().toString().trim();
        String text2 = editText2.getText().toString().trim();

        return text1 != null &&
                text2 != null &&
                text1.length() > 0 &&
                text2.length() > 0 &&
                text1.equals(text2);
    }

    /**
     * Validates birthDate < sysDate
     *
     * @param editText
     * @return
     */
    public static boolean isValidBirthDate(EditText editText){

        String text = editText.getText().toString().trim();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = null;

        try {
            date = format.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date != null && date.compareTo(new Date()) < 0;
    }

}
