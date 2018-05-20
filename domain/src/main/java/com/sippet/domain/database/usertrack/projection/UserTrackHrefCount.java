package com.sippet.domain.database.usertrack.projection;

import java.time.LocalDate;

public interface UserTrackHrefCount {
    LocalDate getDate();
    String getHost();
    String getHref();
    String getPathName();
    String getReferrerHost();
    String getReferrerPath();
    Long getVisitCount();
}
