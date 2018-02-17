package com.sippet.collector;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class UserTrackDto {
    private String host;

    private String href;

    private String pathName;

    private String referrer;
}
