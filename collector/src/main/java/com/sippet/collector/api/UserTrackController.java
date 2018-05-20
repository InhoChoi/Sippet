package com.sippet.collector.api;

import com.sippet.collector.amqp.UserTrackSender;
import com.sippet.domain.service.TrackingUserValidator;
import com.sippet.domain.service.TrackingUser;
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
    private TrackingUserValidator userValidator;

    @Autowired
    private UserTrackSender userTrackSender;

    @PostMapping(path = "/")
    public void addTrack(@RequestBody UserTrackRequest userTrackRequest,
                         @CookieValue(value = "newVisitor", required = false) Boolean newVisistor,
                         @CookieValue(value = "trackingId", required = false) String trackingId,
                         HttpServletResponse httpServletResponse) {

        final TrackingUser trackingUser = userValidator.execute(newVisistor, trackingId);
        bakeTrackingCookie(httpServletResponse, trackingUser);

        userTrackSender.send(userTrackRequest, trackingUser);
    }

    private void bakeTrackingCookie(HttpServletResponse httpServletResponse, TrackingUser trackingUser) {
        final Cookie newVisitorCookie = new Cookie("newVisitor", trackingUser.getNewVisitor().toString());
        newVisitorCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        final Cookie trackingIdCookie = new Cookie("trackingId", trackingUser.getTrackingId());
        trackingIdCookie.setMaxAge(10 * 365 * 24 * 60 * 60);

        httpServletResponse.addCookie(newVisitorCookie);
        httpServletResponse.addCookie(trackingIdCookie);
    }
}
