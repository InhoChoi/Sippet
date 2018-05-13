package com.sippet.domain.domain.usertrack;

import com.sippet.domain.domain.usertrack.projection.UserTrackHrefCount;
import com.sippet.domain.domain.usertrack.projection.UserTrackPathNameCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {
    @Query(value = "SELECT u.pathName as pathName, COUNT(u.pathName) as count FROM UserTrack u GROUP BY u.pathName ORDER BY COUNT(u.pathName) DESC")
    List<UserTrackPathNameCount> findCountGroupByPathName();

    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) as count FROM UserTrack u WHERE u.newVisitor = true")
    int findCountNewVisitor();

    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) FROM UserTrack u")
    int findCountTotalVisitor();

    int countByTrackingId(@Param(value = "trackingId") String trackingId);

    //@Query(value = "Select u.createdAt from UserTrack u where u.trackingId = :trackingId order by u.id DESC")
    //LocalDateTime getLatestDate(@Param(value = "trackingId") String trackingId);

    UserTrack findTopByTrackingIdOrderByIdDesc(@Param(value = "trackingId") String trackingId);

    @Query(value = "SELECT " +
            "DATE(u.createdAt) as date, " +
            "u.host as host, " +
            "u.href as href, " +
            "u.pathName as pathName, " +
            "u.referrerHost as referrerHost, " +
            "u.referrerPath as referrerPath, " +
            "COUNT(u.href) as count " +
            "FROM UserTrack u " +
            "WHERE DATE(u.createdAt) < DATE(now()) " +
            "GROUP BY DATE(u.createdAt), u.host, u.href, u.pathName, u.referrerHost, u.referrerPath " +
            "ORDER BY DATE(u.createdAt)")
    List<UserTrackHrefCount> countGroupByHrefAndDate();
}
