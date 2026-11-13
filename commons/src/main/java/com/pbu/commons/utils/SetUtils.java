package com.pbu.commons.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Project merchant_app_backend
 * @Date 12/11/2026
 * @LocalTime 12:11
 **/
public class SetUtils {

    public static final String GRANTS_SEPARATOR = ",";

    public static Set getSetFromStringWithSeparator(String stringForSet){
        return Arrays.asList(stringForSet.trim().split(GRANTS_SEPARATOR))
                .stream().collect(Collectors.toSet());
    }
}
