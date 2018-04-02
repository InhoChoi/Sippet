package com.sippet.domain.domain.retention;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RetentionPeriodRepository extends JpaRepository<RetentionPeriod, Long>{

    @Query(value = "Select Count(id) from RetentionPeriod where trackingId = :trackingId")
    Long checkDataExist(@Param(value = "trackingId") String trackingId);

    @Query(value = "Select createdAt from UserTrack where trackingId = :trackingId order by id DESC")
    LocalDateTime getLatestDate(@Param(value = "trackingId") String trackingId);

    @Query(value = "Select createdAt from UserTrack where trackingId = :trackingId")
    LocalDateTime getTodayDate(@Param(value = "trackingId") String trackingId);
}