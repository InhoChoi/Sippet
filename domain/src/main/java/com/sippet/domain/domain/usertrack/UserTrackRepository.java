package com.sippet.domain.domain.usertrack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT u.createdAt as date, u.href as href, COUNT(u.href) as count FROM UserTrack u GROUP BY u.href, u.createdAt ORDER BY COUNT(u.href) DESC")
    List<UserTrackHrefCount> countGroupByHrefAndDate();
}
