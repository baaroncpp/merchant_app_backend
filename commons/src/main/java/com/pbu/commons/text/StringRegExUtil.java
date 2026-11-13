package com.pbu.commons.text;


import com.pbu.commons.exceptions.BadRequestException;

import java.util.regex.Pattern;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 12:41
 */
public class StringRegExUtil {

    public static void stringOfStandardPassword(String value, String message){
        if(!standardPassword(value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfOnlyNumbers(String value, String message){
        if(!onlyNumbersAndChars(value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfOnlyNumbersAndChars(String value, String message){
        if(!Pattern.matches("^[a-zA-Z0-9]+$", value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfOnlyNumbersAndCharsAndOneSpace(String value, String message){
        if(!Pattern.matches("^[a-zA-Z0-9 ]+$", value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfOnlyCharsNoneCaseSensitive(String value, String message){
        if(!Pattern.matches("^[a-zA-Z]+$", value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfOnlyCharsNoneCaseSensitiveAndOneSpace(String value, String message){
        if(!Pattern.matches("^[a-zA-Z ]+$", value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfOnlyLowerCase(String value, String message){
        if(!Pattern.matches("^[a-z]+$", value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfOnlyUpperCase(String value, String message){
        if(!Pattern.matches("^[A-Z]+$", value)){
            throw new BadRequestException(message);
        }
    }

    public static void stringOfInternationalPhoneNumber(String value, String message, Object ... params){
        if(!isStandardInternationalPhoneNumber( value)){
            throw new BadRequestException(message, params);
        }
    }

    public static boolean isStandardInternationalPhoneNumber(String value){
        if (value == null) {
            return false;
        }
        // Remove any whitespace, dashes, dots, or parentheses for consistent validation
        String cleanedValue = value.replaceAll("[\\s-.()]", "");
        // Regex: Starts with +, followed by 1-3 digits (country code), then 6-12 digits
        return Pattern.matches("^\\+[1-9][0-9]{0,2}[0-9]{6,12}$", cleanedValue);
    }

    public static void stringOfEmail(String value, String message, Object ... params){
        if(!validEmail(value)){
            throw new BadRequestException(message, params);
        }
    }

    public static boolean validEmail(String value){
        return Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", value);
    }

    public static boolean standardPassword(String value){
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", value);
    }

    public static boolean onlyNumbersAndChars(String value){
        return Pattern.matches("^[a-zA-Z0-9]+$", value);
    }
}
