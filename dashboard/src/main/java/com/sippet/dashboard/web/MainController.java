package com.sippet.dashboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @RequestMapping("/")
    public String main() {
        return "redirect:/dashboard";
    }

    @RequestMapping("/hello")
    @ResponseBody
    private String hello() {
        return "Hello Sippet Dashboard";
    }
}
