package com.sippet.domain.builder;

import com.sippet.domain.database.usertrack.UserTrack;
import com.sippet.domain.database.usertrack.UserTrackDto;

public class UserTrackBuilder extends AbstractUserTrackBuilder{

    @Override
    public <T> Object build(T t) {
        return userTrackBuild((UserTrackDto) t);
    }
}
