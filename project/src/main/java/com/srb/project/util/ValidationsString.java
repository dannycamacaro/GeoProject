package com.srb.project.util;

public class ValidationsString {

    public static final boolean isEmptyOrNull(String value){

        boolean validation = false;

         validation = (value == null || value.isEmpty() ) ? true : false;

         return validation;
    }
}
