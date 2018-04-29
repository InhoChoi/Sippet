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
    public RetentionPeriod produce(UserTrackRepository userTrackRepo, RetentionPeriodRepository retentionRepo, String trackingId) {
        //TODO. 만약 UserTrack 테이블에 해당 trackingID의 데이터가 없으면 어떻게 할 것인지 => 쿠키가 뉴유저인지 아닌지로 판별하면 될듯.
        //TODO. 캐시가 필요할듯. 이 매서드 들어올때마다 디비로 접근해서 뉴유저 판별하면 너무 자원소모가 크다.
        //TODO. 그리고 뉴유저면 초기 날짜를 어떻게 할건지. => 0일로 초기화?
        //TODO. 그리고 정책을 정해야 할듯. 기간이 유효하지 않으면(30일이 넘으면) 저장할 것인지, 무시할 것인지, 다르게 저장할 것인지 등.
        //TODO. 그리고 질문 2가지 -> 1. 여기 위에 autowired로 repository들을 추가하면 빈이 없다고 에러가 난다.
        //TODO. 2. 아래 데이터 유무 체크 부분을 좀더 잘 고칠수 없을까?

        Long validPeriod = 0L;

//        if(retentionRepo.checkDataExist(trackingId) > 0) {
//            System.out.println("Data is exist!!");
//            validPeriod =
//                    calculate(userTrackRepo.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), getToady());
//        }

        if(retentionRepo.checkTodayDataExist(trackingId, LocalDate.now()) > 0) {
            System.out.println("Today Data is exist!!");
            return null;
        }

        if(validPeriod <= 30){
            log.info("This tracking id's retention period is valid.");

            return new RetentionPeriod().builder()
                    .trackingId(trackingId)
                    .retentionPeriod(validPeriod)
                    .eventDate(LocalDate.now())
                    .build();
        }
        return new RetentionPeriod().builder()
                .trackingId(trackingId)
                .retentionPeriod(invalidPeriod)
                .eventDate(LocalDate.now())
                .build();
    }
}
