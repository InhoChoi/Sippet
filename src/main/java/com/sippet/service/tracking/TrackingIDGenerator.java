package com.sippet.service.tracking;

import java.util.UUID;

class TrackingIDGenerator {
    static String generate() {
        return UUID.randomUUID().toString();
    }
}
