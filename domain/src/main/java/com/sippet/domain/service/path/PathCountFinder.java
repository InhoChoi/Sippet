package com.sippet.domain.service.path;

import com.sippet.domain.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sippet.domain.util.LocalDateTimeUtils.endToday;
import static com.sippet.domain.util.LocalDateTimeUtils.startToday;

@Service
public class PathCountFinder {
    @Autowired
    private UserTrackRepository userTrackRepository;

    public List<PathCount> findPathTodayCount() {
        return userTrackRepository.findCountGroupByPathName(startToday(), endToday());
    }
}
