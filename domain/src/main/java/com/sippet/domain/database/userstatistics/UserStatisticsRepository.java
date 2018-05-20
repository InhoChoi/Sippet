package com.sippet.domain.database.userstatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long> {
//    @Query(value = "SELECT COUNT(DISTINCT u.trackingId) as count FROM UserTrack u WHERE u.newVisitor = true")
//    int findCountNewVisitor();
}
