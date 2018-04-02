package com.sippet.domain.service;

import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class RetentionPeriod {
    //TODO. 이 autowired가 에러를 발생한다 -> RetentionPeriodRepository를 찾을 수 없다나..
//    @Autowired
//    private RetentionPeriodRepository repository;

    /**
     * Get today date
     *
     * @return today date
     */
    private LocalDateTime getToady() {
        return LocalDateTime.now();
    }

    /**
     * Calculate to get between period of two dates
     *
     * @param latestDate
     * @param todayDate
     * @return between period of two date
     */
    private Long calculate(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(todayDate.toLocalDate(), latestDate.toLocalDate());
    }

    /**
     * Check difference of two dates were over month or not
     *
     * @param latestDate
     * @param todayDate
     * @return {@code true}, if two dates difference was less than month
     */
    private boolean checkValid(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(todayDate.toLocalDate(), latestDate.toLocalDate()) <= 30;
    }

    /**
     * @return
     */
//    public Long produce(RetentionPeriodRepository repository, String trackingId) {
    public void produce(UserTrackRepository repository, String trackingId) {

//        List<UserTrack> userTrack = repository.findTopByOrderByTrackingIdDesc(trackingId);
        UserTrack userTrack = repository.findTopByTrackingIdOrderByIdDesc(trackingId);

        log.info("User Track Is : {}", userTrack);
//        if(checkValid(repository.getLatestDate(trackingId), getToady())){
//            log.info("This tracking id's retention period is valid.");
//            log.info("" + calculate(repository.getLatestDate(trackingId), getToady()));
//        }

        log.info("After if area.");
//        return 0L;
    }
}
