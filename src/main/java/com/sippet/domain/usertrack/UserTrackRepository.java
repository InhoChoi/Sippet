package com.sippet.domain.usertrack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {
    @Query(value = "SELECT u.pathName as pathName, COUNT(u.pathName) as count " +
            "FROM UserTrack u " +
            "WHERE u.createdAt BETWEEN :start AND :end " +
            "GROUP BY u.pathName " +
            "ORDER BY COUNT(u.pathName) DESC")
    List<UserTrackPathNameCount> findCountGroupByPathName(@Param("start") LocalDateTime start,
                                                          @Param("end") LocalDateTime end);

    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) as count " +
            "FROM UserTrack u " +
            "WHERE u.newVisitor = true " +
            "AND u.createdAt BETWEEN :start AND :end")
    int findCountNewVisitor(@Param("start") LocalDateTime start,
                            @Param("end") LocalDateTime end);

    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) " +
            "FROM UserTrack u " +
            "WHERE u.createdAt BETWEEN :start AND :end ")
    int findCountTotalVisitor(@Param("start") LocalDateTime start,
                              @Param("end") LocalDateTime end);

    @Query(value = "SELECT DISTINCT(u.trackingId) " +
            "FROM UserTrack u " +
            "WHERE u.createdAt BETWEEN :start AND :end " +
            "AND u.trackingId > :lastTrackingId " +
            "ORDER BY u.trackingId ASC " +
            "LIMIT :size")
    List<String> findTrackingId(@Param("start") LocalDateTime start,
                                @Param("end") LocalDateTime end,
                                @Param("lastTrackingId") String lastTrackingId,
                                @Param("size") int size);

    @Query(value = "SELECT MAX(u.createdAt) " +
            "FROM UserTrack u " +
            "WHERE u.trackingId in :trackingIdList " +
            "AND u.createdAt BETWEEN :start AND :end " +
            "GROUP BY u.trackingId")
    List<LocalDateTime> findLastVisitDateTime(@Param("trackingIdList") List<String> trackingIdList,
                                              @Param("start") LocalDateTime start,
                                              @Param("end") LocalDateTime end);
}
