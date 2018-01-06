package com.sippet.service.tracking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class TrackingResult {
    private String trackingId;
    private Boolean newVisitor;

    static TrackingResult newUser() {
        TrackingResult trackingResult = new TrackingResult();
        trackingResult.trackingId = TrackingIDGenerator.generate();
        trackingResult.newVisitor = true;
        return trackingResult;
    }
}
