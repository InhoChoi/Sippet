package com.sippet.domain.builder;

import com.sippet.domain.database.usertrack.UserTrackDto;
import com.sippet.domain.service.TrackingUser;
import com.sippet.domain.util.UserTrackConverter;

public abstract class AbstractUserTrackDtoBuilder implements Builders {
//    protected UserTrackDto userTrackDtoBuild(UserTrackRequest userTrackRequest, TrackingUser trackingUser) {
//        final String referrer_path = UserTrackConverter.createReferrerPath(userTrackRequest.getReferrer());
//        final String referrer_host = UserTrackConverter.createReferrerHost(userTrackRequest.getReferrer(), referrer_path);
//
//        return UserTrackDto.builder()
//                .pathName(userTrackRequest.getPathName())
//                .referrerHost(referrer_host)
//                .referrerPath(referrer_path)
//                .href(userTrackRequest.getHref())
//                .host(userTrackRequest.getHost())
//                .trackingId(trackingUser.getTrackingId())
//                .newVisitor(trackingUser.getNewVisitor())
//                .build();
//    }
}
