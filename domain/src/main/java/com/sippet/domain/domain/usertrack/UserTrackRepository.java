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

    //@Query(value = "Select u.createdAt from UserTrack u where u.trackingId = :trackingId order by u.id DESC")
    //LocalDateTime getLatestDate(@Param(value = "trackingId") String trackingId);


//    List<UserTrack> findTopByOrderByTrackingIdDesc(String trackingId);
//    UserTrack findTopByOrderByTrackingIdDesc(String trackingId);
//    @Query(value = "SELECT u.trackingId, u.createdAt FROM UserTrack u " +
//            "WHERE u.trackingId = :trackingId ORDER BY id DESC LIMIT 1")
    UserTrack findTopByTrackingIdOrderByIdDesc(@Param(value = "trackingId") String trackingId);
}
