package com.sippet.dashboard.api;

import com.sippet.domain.database.userstatistics.UserStatisticsRepository;
import com.sippet.domain.database.userstatistics.UserStatisticsDateCount;
import com.sippet.domain.database.usertrack.UserTrackPathNameCount;
import com.sippet.domain.database.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    @Autowired
    private UserStatisticsRepository userStatisticsRepository;

    @GetMapping(path = "/group_by_count/path_name")
    public List<UserTrackPathNameCount> getPathNameCountList() {
        System.out.println("check /group_by_count/path_name!!!");
        System.out.println(userTrackRepository.findCountGroupByPathName());
        return userTrackRepository.findCountGroupByPathName();
    }

    @GetMapping(path = "/group_by_count/visit_count")
    public String getVisitCountList(Model model) {
        List<UserStatisticsDateCount> visitList = userStatisticsRepository.findVisitCountGroupByDate();

        System.out.println("check /group_by_count/visit_count!!!");
        model.addAttribute("visitCountArray", visitList);

        return "dashboard/dashboard";
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
