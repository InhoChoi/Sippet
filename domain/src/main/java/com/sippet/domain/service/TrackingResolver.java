package com.sippet.domain.service;

import com.sippet.domain.util.NullChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TrackingResolver {
    public TrackingResult resolve(Boolean newVisitor, String trackingId) {

        if(NullChecker.check(newVisitor, trackingId)) {
            return TrackingResult.newUser();
        }

        // newVisitor와 TrackingId 둘다 존재하는 경우는 newVisitor로 판단하지 않는다.
        return TrackingResult.create(trackingId, false);
    }
}
