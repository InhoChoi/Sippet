package com.sippet.domain.service;

import com.sippet.domain.domain.retention.RetentionPeriod;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class PeriodCalculator {

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
        return DAYS.between(latestDate.toLocalDate(), todayDate.toLocalDate());
    }

    /**
     * Produce Retention period object of calculated period
     *
     * @param userTrackRepo
     * @param trackingId
     * @return Class RetentiodPeriod object of calculated period
     */
    public RetentionPeriod produce(UserTrackRepository userTrackRepo, String trackingId) {
        //TODO. 만약 UserTrack 테이블에 해당 trackingID의 데이터가 없으면 어떻게 할 것인지 => 쿠키가 뉴유저인지 아닌지로 판별하면 될듯.
        //TODO. 그리고 질문 2가지 -> 1. 여기 위에 autowired로 repository들을 추가하면 빈이 없다고 에러가 난다.
        //TODO. 2. 아래 validPeriod 를 final로 고칠 수 있을까??

        Long validPeriod;
        try{
            validPeriod = calculate(userTrackRepo.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), getToady());
        } catch (Exception e) {
            e.printStackTrace();
            validPeriod = 0L;
        }

        return new RetentionPeriod().builder()
                .trackingId(trackingId)
                .retentionPeriod(validPeriod)
                .eventDate(LocalDate.now())
                .build();
    }
}
