package com.pbu.commons.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Project merchant-backend
 * @Date 11/12/26
 * @LocalTime 12:11 PM
 **/
public class SetUtils {

    public static final String GRANTS_SEPARATOR = ",";

    public static Set getSetFromStringWithSeparator(String stringForSet){
        return Arrays.asList(stringForSet.trim().split(GRANTS_SEPARATOR))
                .stream().collect(Collectors.toSet());
    }
}
