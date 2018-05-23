package com.sippet.dashboard.api;

import com.sippet.domain.database.usertrack.projection.UserTrackPathNameCount;
import com.sippet.domain.database.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private UserTrackRepository userTrackRepository;

    @GetMapping(path = "/group_by_count/path_name")
    public List<UserTrackPathNameCount> getPathNameCountList() {
        return userTrackRepository.findCountGroupByPathName();
    }

    @GetMapping(path = "/count/visitor")
    public VisitorCount getNewVisitorCount() {
        return VisitorCount.create(
                userTrackRepository.findCountTotalVisitor(),
                userTrackRepository.findCountNewVisitor()
        );
    }
}
