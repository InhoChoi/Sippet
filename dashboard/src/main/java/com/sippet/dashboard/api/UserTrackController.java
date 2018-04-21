package com.sippet.dashboard.api;

import com.sippet.domain.service.path.PathCount;
import com.sippet.domain.service.path.PathCountFinder;
import com.sippet.domain.service.visitor.VisitorCount;
import com.sippet.domain.service.visitor.VisitorCountFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private VisitorCountFinder visitorCountFinder;

    @Autowired
    private PathCountFinder pathCountFinder;

    @GetMapping(path = "/path-count/today")
    public List<PathCount> getPathNameCountList() {
        return pathCountFinder.findPathTodayCount();
    }

    @GetMapping(path = "/visitor-count/today")
    public VisitorCount getNewVisitorCount() {
        return visitorCountFinder.findVisitorTodayCount();
    }
}
