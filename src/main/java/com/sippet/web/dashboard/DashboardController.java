package com.sippet.web.dashboard;

import com.sippet.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private UserTrackRepository userTrackRepository;

    @RequestMapping
    public String dashbaord(Model model) {
        final LocalDateTime start = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        final LocalDateTime end = start.with(LocalTime.MAX);

        model.addAttribute("totalVisitorCount", userTrackRepository.findCountTotalVisitor(start, end));
        model.addAttribute("newVisitorCount", userTrackRepository.findCountNewVisitor(start, end));
        model.addAttribute("year", start.getYear());
        model.addAttribute("month", start.getMonthValue());
        model.addAttribute("day", start.getDayOfMonth());
        return "dashboard/dashboard";
    }
}
