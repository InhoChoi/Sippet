package com.sippet.service;

import com.sippet.util.NullResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


@Slf4j
@Service
public class TrackingResolver {
    public TrackingResult resolve(Boolean newVisitor, String trackingId) {
//        if (Objects.isNull(newVisitor) || Objects.isNull(trackingId)) {
//            return TrackingResult.newUser();
//        }
        if(NullResolver.resolveArrayList(new ArrayList<Object>(Arrays.asList(newVisitor, trackingId)))) {
            return TrackingResult.newUser();
        }

        // newVisitor와 TrackingId 둘다 존재하는 경우는 newVisitor로 판단하지 않는다.
        return TrackingResult.create(trackingId, false);
    }
}
