package com.sippet.api;

import com.sippet.domain.usertrack.UserTrack;
import com.sippet.domain.usertrack.UserTrackPathNameCount;
import com.sippet.domain.usertrack.UserTrackRepository;
import com.sippet.service.statatis.PathNameCountFinder;
import com.sippet.service.tracking.TrackingResolver;
import com.sippet.service.tracking.TrackingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private PathNameCountFinder pathNameCountFinder;

    @Autowired
    private UserTrackRepository userTrackRepository;

    @Autowired
    private TrackingResolver trackingResolver;

    @PostMapping(path = "/")
    public void addTrack(@RequestBody UserTrackDto userTrackDto,
                         @CookieValue(value = "newVisitor", required = false) Boolean newVisistor,
                         @CookieValue(value = "trackingId", required = false) String trackingId,
                         HttpServletResponse httpServletResponse) {
        final TrackingResult trackingResult = trackingResolver.resolve(newVisistor, trackingId);

        bakeTrackingCookie(httpServletResponse, trackingResult);

        final UserTrack userTrack = UserTrack.builder()
                .pathName(userTrackDto.getPathName())
                .referrer(userTrackDto.getReferrer())
                .href(userTrackDto.getHref())
                .host(userTrackDto.getHost())
                .trackingId(trackingResult.getTrackingId())
                .newVisitor(trackingResult.getNewVisitor())
                .build();

        userTrackRepository.save(userTrack);
    }

    private void bakeTrackingCookie(HttpServletResponse httpServletResponse, TrackingResult trackingResult) {
        final Cookie newVisitorCookie = new Cookie("newVisitor", trackingResult.getNewVisitor().toString());
        newVisitorCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        final Cookie trackingIdCookie = new Cookie("trackingId", trackingResult.getTrackingId());
        trackingIdCookie.setMaxAge(10 * 365 * 24 * 60 * 60);

        httpServletResponse.addCookie(newVisitorCookie);
        httpServletResponse.addCookie(trackingIdCookie);
    }

    @GetMapping(path = "/group_by_count/path_name")
    public List<UserTrackPathNameCount> getPathNameCountList() {
        return pathNameCountFinder.findTodayPathNameCount();
    }
}
