package com.sippet.dashboard.web.dashboard;

import com.sippet.domain.database.userstatistics.UserStatisticsRepository;
import com.sippet.domain.database.userstatistics.UserStatisticsDateCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserStatisticsRepository userStatisticsRepository;

    @RequestMapping
    public String dashbaord(Model model) {
        List<UserStatisticsDateCount> visitList = userStatisticsRepository.findVisitCountGroupByDate();
        model.addAttribute("visitCountArray", visitList);

        return "dashboard/dashboard";
    }
}
