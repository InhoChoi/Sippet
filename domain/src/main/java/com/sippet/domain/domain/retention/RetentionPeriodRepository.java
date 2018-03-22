package com.sippet.domain.domain.retention;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RetentionPeriodRepository extends JpaRepository<RetentionPeriod, Long>{

    @Query(value = "Select createdAt from UserTrack where trackingId = :trackingId")
    LocalDateTime getLatestDate(String trackingId);

    @Query(value = "Select createdAt from UserTrack where trackingId = :trackingId")
    LocalDateTime getTodayDate(String trackingId);
}
