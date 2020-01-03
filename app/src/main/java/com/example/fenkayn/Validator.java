package com.example.fenkayn;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Validator {

    public static final int EMAIL = 0;
    public static final int PASSWORD = 1;

    /**
     * Validates several types
     *
     * @param inputType
     * @param editText
     * @return
     */
    public static boolean isValid(int inputType, EditText editText){

        switch (inputType){
            case EMAIL:
                return isValidEmail(editText);

            case PASSWORD:
                return isValidLength(editText, 6);

            default:
                return false;

        }
    }

    /**
     * Validates email
     *
     * @param editText
     * @return
     */
    private static boolean isValidEmail(EditText editText){

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
    private static boolean isValidLength(EditText editText, int length){

        String text = editText.getText().toString().trim();
        return 0 < text.length() && text.length()< length;
    }

}
