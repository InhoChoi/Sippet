package com.sippet.domain.util;

import java.util.Objects;

public class NullChecker {

    public static boolean check(Object ... objects) {

        for(Object object : objects) {
            if(Objects.isNull(object)) {
                return true;
            }
        }

        return false;
    }
}
