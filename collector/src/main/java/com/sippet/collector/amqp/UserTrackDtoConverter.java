package com.sippet.collector.amqp;

import com.sippet.collector.api.UserTrackRequest;
import com.sippet.domain.database.usertrack.UserTrackDto;
import com.sippet.domain.service.TrackingUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
class UserTrackDtoConverter {
    public UserTrackDto convert(UserTrackRequest userTrackRequest, TrackingUser trackingUser) {
        final String referrerPath = createReferrerPath(userTrackRequest.getReferrer());
        final String referrerHost = createReferrerHost(userTrackRequest.getReferrer(), referrerPath);

        return UserTrackDto.builder()
                .pathName(userTrackRequest.getPathName())
                .referrerHost(referrerHost)
                .referrerPath(referrerPath)
                .href(userTrackRequest.getHref())
                .host(userTrackRequest.getHost())
                .trackingId(trackingUser.getTrackingId())
                .newVisitor(trackingUser.getNewVisitor())
                .build();
    }

    public static String createReferrerPath(String path) {
        try {
            return new URI(path).getPath();
        } catch (URISyntaxException e) {
            log.warn("URI Syntax Error", e);
            return StringUtils.EMPTY;
        }
    }

    public static String createReferrerHost(String referrer, String substringPosition) {
        return StringUtils.substringBefore(referrer, substringPosition);
    }
}
