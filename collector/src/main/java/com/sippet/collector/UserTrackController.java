package com.sippet.collector;

import com.sippet.domain.service.TrackingResolver;
import com.sippet.domain.service.TrackingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private TrackingResolver trackingResolver;

    @Autowired
    private UserTrackSender userTrackSender;

    @PostMapping(path = "/")
    public void addTrack(@RequestBody UserTrackRequest userTrackRequest,
                         @CookieValue(value = "newVisitor", required = false) Boolean newVisistor,
                         @CookieValue(value = "trackingId", required = false) String trackingId,
                         HttpServletResponse httpServletResponse) {
        final TrackingResult trackingResult = trackingResolver.resolve(newVisistor, trackingId);
        bakeTrackingCookie(httpServletResponse, trackingResult);

        userTrackSender.send(userTrackRequest, trackingResult);
    }

    private void bakeTrackingCookie(HttpServletResponse httpServletResponse, TrackingResult trackingResult) {
        final Cookie newVisitorCookie = new Cookie("newVisitor", trackingResult.getNewVisitor().toString());
        newVisitorCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        final Cookie trackingIdCookie = new Cookie("trackingId", trackingResult.getTrackingId());
        trackingIdCookie.setMaxAge(10 * 365 * 24 * 60 * 60);

        httpServletResponse.addCookie(newVisitorCookie);
        httpServletResponse.addCookie(trackingIdCookie);
    }
}
