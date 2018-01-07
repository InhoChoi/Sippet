package com.sippet.service;

import java.util.UUID;

class TrackingIDGenerator {
    static String generate() {
        return UUID.randomUUID().toString();
    }
}
