package com.sippet.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "returnVisitUser")
public class TrackingUser {
    private String trackingId;
    private Boolean newVisitor;

    public static TrackingUser newUser() {
        TrackingUser trackingUser = new TrackingUser();
        trackingUser.trackingId = TrackingIDGenerator.generate();
        trackingUser.newVisitor = true;
        return trackingUser;
    }
}
