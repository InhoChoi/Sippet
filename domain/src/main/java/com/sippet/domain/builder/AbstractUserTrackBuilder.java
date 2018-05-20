package com.sippet.domain.builder;

import com.sippet.domain.database.usertrack.UserTrack;
import com.sippet.domain.database.usertrack.UserTrackDto;
import com.sippet.domain.util.UserTrackConverter;

public abstract class AbstractUserTrackBuilder implements Builders{
//    protected UserTrack userTrackBuild(UserTrackDto userTrackDto) {
    protected UserTrack userTrackBuild(UserTrackDto userTrackDto) {
        return UserTrack.builder()
                .host(userTrackDto.getHost())
                .href(userTrackDto.getHref())
                .referrerHost(userTrackDto.getReferrerHost())
                .referrerPath(userTrackDto.getReferrerPath())
                .pathName(userTrackDto.getPathName())
                .newVisitor(userTrackDto.getNewVisitor())
                .trackingId(userTrackDto.getTrackingId())
                .build();
    }
}
