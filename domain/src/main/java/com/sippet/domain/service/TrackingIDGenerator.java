package com.sippet.domain.service;

import java.util.UUID;

class TrackingIDGenerator {
    static String generate() {
        return UUID.randomUUID().toString();
    }
}
