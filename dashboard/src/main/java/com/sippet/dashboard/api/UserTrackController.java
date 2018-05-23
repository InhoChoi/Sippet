package com.sippet.dashboard.api;

import com.sippet.domain.database.usertrack.projection.UserTrackPathNameCount;
import com.sippet.domain.database.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private UserTrackRepository userTrackRepository;

    @GetMapping(path = "/group_by_count/path_name")
    public List<UserTrackPathNameCount> getPathNameCountList() {
        System.out.println("check /group_by_count/path_name!!!");
        System.out.println(userTrackRepository.findCountGroupByPathName());
        return userTrackRepository.findCountGroupByPathName();
    }

    @GetMapping(path = "/count/visitor")
    public VisitorCount getNewVisitorCount() {
        System.out.println("check /count/visitor!!!");
        System.out.println(userTrackRepository.findCountTotalVisitor());
        System.out.println(userTrackRepository.findCountNewVisitor());
        return VisitorCount.create(
                userTrackRepository.findCountTotalVisitor(),
                userTrackRepository.findCountNewVisitor()
        );
    }
}
