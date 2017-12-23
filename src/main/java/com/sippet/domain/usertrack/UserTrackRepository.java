package com.sippet.domain.usertrack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {
    @Query(value = "SELECT u.pathName as pathName, COUNT(u.pathName) as count FROM UserTrack u GROUP BY u.pathName ORDER BY COUNT(u.pathName) DESC")
    List<UserTrackPathNameCount> findCountGroupByPathName();
}
