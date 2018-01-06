package com.sippet.service.usertrack;

import com.sippet.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserTrackFinder {
    @Autowired
    private UserTrackRepository userTrackRepository;

    public List<String> findTrackingId(LocalDateTime start, LocalDateTime end, String lastTrackingId, int size) {
        return userTrackRepository.findTrackingId(start, end, lastTrackingId, size);
    }

    public List<LocalDateTime> findLastVisitedDay(LocalDateTime start, LocalDateTime end, List<String> trackingIdList) {
        return userTrackRepository.findLastVisitDateTime(trackingIdList, start, end);
    }
}
