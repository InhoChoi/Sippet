package com.sippet.domain.util;

import com.sippet.domain.service.TrackingUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class UserTrackConverter {
    public static String createReferrerPath(String referrer) {
        try {
            return new URI(referrer).getPath();
        } catch (URISyntaxException e) {
            log.warn("URI Syntax Error", e);
            return StringUtils.EMPTY;
        }
    }

    public static String createReferrerHost(String referrer, String substringPosition) {
        return StringUtils.substringBefore(referrer, substringPosition);
    }

//    public static TrackingUser createTrackingUser() {
//        return ;
//    }
}
