package com.sippet.collector.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserTrackRequest {
    private String host;

    private String href;

    private String pathName;

    private String referrer;
}
