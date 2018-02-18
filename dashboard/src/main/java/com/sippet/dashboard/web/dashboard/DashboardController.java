package com.sippet.dashboard.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping
    public String dashbaord(Model model) {
        return "dashboard/dashboard";
    }
}
