package com.sippet.collector.amqp;

import com.sippet.collector.api.UserTrackRequest;
import com.sippet.domain.domain.usertrack.UserTrackDto;
import com.sippet.domain.service.trakcing.TrackingResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
class UserTrackDtoConverter {
    UserTrackDto convert(UserTrackRequest userTrackRequest, TrackingResult trackingResult) {
        final String referrer_path = parseReferrerPath(userTrackRequest.getReferrer());
        final String referrer_host = StringUtils.substringBefore(userTrackRequest.getReferrer(), referrer_path);

        return UserTrackDto.builder()
                .pathName(userTrackRequest.getPathName())
                .referrer_host(referrer_host)
                .referrer_path(referrer_path)
                .href(userTrackRequest.getHref())
                .host(userTrackRequest.getHost())
                .trackingId(trackingResult.getTrackingId())
                .newVisitor(trackingResult.getNewVisitor())
                .build();
    }

    private String parseReferrerPath(String path) {
        try {
            return new URI(path).getPath();
        } catch (URISyntaxException e) {
            log.warn("URI Syntax Error", e);
            return StringUtils.EMPTY;
        }
    }
}
