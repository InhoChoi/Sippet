package com.sippet.domain.domain.usertrack;

import java.time.LocalDate;

public interface UserTrackHrefCount {
    LocalDate getDate();
    String getHost();
    String getHref();
    String getPathName();
    String getReferrerHost();
    String getReferrerPath();
    int getCount();
}
