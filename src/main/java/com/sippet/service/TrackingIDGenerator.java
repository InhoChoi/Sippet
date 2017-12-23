package com.sippet.service;

import java.util.UUID;

public class TrackingIDGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
