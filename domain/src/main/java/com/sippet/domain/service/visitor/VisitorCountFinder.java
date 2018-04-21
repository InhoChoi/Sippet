package com.sippet.domain.service.visitor;

import com.sippet.domain.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.sippet.domain.util.LocalDateTimeUtils.endToday;
import static com.sippet.domain.util.LocalDateTimeUtils.startToday;

@Service
public class VisitorCountFinder {
    @Autowired
    private UserTrackRepository userTrackRepository;

    public VisitorCount findVisitorTodayCount() {
        return VisitorCount.create(
                userTrackRepository.findCountTotalVisitor(startToday(), endToday()),
                userTrackRepository.findCountNewVisitor(startToday(), endToday()),
                LocalDate.now()
        );
    }
}
