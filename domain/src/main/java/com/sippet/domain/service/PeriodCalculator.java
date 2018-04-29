package com.sippet.domain.service;

import com.sippet.domain.domain.retention.RetentionPeriod;
import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class PeriodCalculator {

    @Value("${period.invalid}")
    private Long invalidPeriod;

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
//        System.out.println(latestDate.minus(todayDate));
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
    public RetentionPeriod produce(UserTrackRepository userTrackRepo, String trackingId) {
        //TODO. 만약 UserTrack 테이블에 해당 trackingID의 데이터가 없으면 어떻게 할 것인지 => 쿠키가 뉴유저인지 아닌지로 판별하면 될듯.
        //TODO. 그리고 질문 2가지 -> 1. 여기 위에 autowired로 repository들을 추가하면 빈이 없다고 에러가 난다.
        //TODO. 2. 아래 데이터 유무 체크 부분을 좀더 잘 고칠수 없을까?

        final Long validPeriod = calculate(userTrackRepo.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), getToady());

        return new RetentionPeriod().builder()
                .trackingId(trackingId)
//                .retentionPeriod(invalidPeriod)
                .retentionPeriod(validPeriod)
                .eventDate(LocalDate.now())
                .build();
    }
}
