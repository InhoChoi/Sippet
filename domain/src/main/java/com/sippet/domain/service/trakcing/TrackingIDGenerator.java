package com.sippet.domain.service.trakcing;

import java.util.UUID;

class TrackingIDGenerator {
    static String generate() {
        return UUID.randomUUID().toString();
    }
}
