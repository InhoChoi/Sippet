package com.sippet.domain.domain.usertrack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTrackDto {
    private String host;

    private String href;

    private String pathName;

    private String referrerHost;

    private String referrerPath;

    private String trackingId;

    private Boolean newVisitor;
}
