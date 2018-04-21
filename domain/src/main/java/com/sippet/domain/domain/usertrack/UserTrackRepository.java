package com.sippet.domain.domain.usertrack;

import com.sippet.domain.service.path.PathCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {
    @Query(value = "SELECT u.pathName as pathName, COUNT(u.pathName) as count " +
            "FROM UserTrack u " +
            "WHERE u.createdAt BETWEEN :start and :end " +
            "GROUP BY u.pathName " +
            "ORDER BY COUNT(u.pathName) DESC")
    List<PathCount> findCountGroupByPathName(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) as count " +
            "FROM UserTrack u " +
            "WHERE u.createdAt BETWEEN  :start and :end " +
            "AND u.newVisitor = true")
    int findCountNewVisitor(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) " +
            "FROM UserTrack u " +
            "WHERE u.createdAt BETWEEN :start and :end ")
    int findCountTotalVisitor(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
