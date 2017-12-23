package com.sippet.util;

import java.util.ArrayList;
import java.util.Objects;

public class NullResolver {

    public static boolean resolveArrayList(ArrayList<Object> objectsList) {
        boolean isContain = false;

        for(Object object : objectsList) {
            if(Objects.isNull(object)) {
                return true;
            }
        }

        return isContain;
    }
}
