package com.sippet.domain.database.userstatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long> {
//    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) as count FROM UserTrack u WHERE u.newVisitor = true")
//    int findCountNewVisitor();

    @Query(value = "SELECT u.pathName as pathName, DATE(u.date) as date, u.visitCount as visitCount " +
                    "FROM UserStatistics u GROUP BY DATE(u.date), u.pathName, u.visitCount")
    List<UserStatisticsDateCount> findVisitCountGroupByDate();
}
