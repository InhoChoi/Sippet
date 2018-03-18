package com.sippet.domain.service;

import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class RetentionPeriod {
    //TODO. 이 autowired가 에러를 발생한다 -> RetentionPeriodRepository를 찾을 수 없다나..
//    @Autowired
//    private RetentionPeriodRepository repository;

    /**
     * Calculate to get between period of two dates
     * @param latestDate
     * @param todayDate
     * @return between period of two date
     */
    private Long calculate(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(todayDate.toLocalDate(), latestDate.toLocalDate());
    }

    /**
     * Check difference of two dates were over month or not
     * @param latestDate
     * @param todayDate
     * @return {@code true}, if two dates difference was less than month
     */
    private boolean checkValid(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(todayDate.toLocalDate(), latestDate.toLocalDate()) > 30;
    }

//    /**
//     * Get latest date of tracking id
//     * @return latest date
//     */
//    private LocalDateTime getLatest() {
//        return ;
//    }
//
//    /**
//     * Get today date of tracking id
//     * @return today date
//     */
//    private LocalDateTime getToday() {
//        return ;
//    }

    /**
     *
     * @return
     */
//    public Long produce(String trackingId) {
//        if(checkValid(repository.getLatestDate(trackingId), repository.getTodayDate(trackingId))){
////            return calculate();
//            log.info("This tracking id's retention period is valid.");
//        }
//        return 0L;
//    }
}
