package com.sippet.service.statatis;

import com.sippet.domain.usertrack.UserTrackPathNameCount;
import com.sippet.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PathNameCountFinder {
    @Autowired
    private UserTrackRepository userTrackRepository;

    public List<UserTrackPathNameCount> findTodayPathNameCount() {
        final LocalDateTime start = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        final LocalDateTime end = start.with(LocalTime.MAX);

        return userTrackRepository.findCountGroupByPathName(start, end);
    }
}
