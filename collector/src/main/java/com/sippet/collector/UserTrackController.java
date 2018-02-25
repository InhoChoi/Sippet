package com.sippet.collector;

import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackDto;
import com.sippet.domain.service.TrackingResolver;
import com.sippet.domain.service.TrackingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private TrackingResolver trackingResolver;

    @Autowired
    private UserTrackProducer userTrackProducer;

    @PostMapping(path = "/")
    public void addTrack(@RequestBody UserTrackRequest userTrackRequest,
                         @CookieValue(value = "newVisitor", required = false) Boolean newVisistor,
                         @CookieValue(value = "trackingId", required = false) String trackingId,
                         HttpServletResponse httpServletResponse) {
        final TrackingResult trackingResult = trackingResolver.resolve(newVisistor, trackingId);
        bakeTrackingCookie(httpServletResponse, trackingResult);

        String referrer_host = "";
        String referrer_path = "";
        try {
            referrer_host = String.valueOf(
                    new URI(userTrackRequest.getReferrer()
                            .substring(0, userTrackRequest.getReferrer().length()
                                    - new URI(userTrackRequest.getReferrer()).getPath().length())));
            referrer_path = new URI(userTrackRequest.getReferrer()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        final UserTrackDto userTrack = UserTrackDto.builder()
                .pathName(userTrackRequest.getPathName())
//                .referrer(userTrackRequest.getReferrer())
                .referrer_host(referrer_host)
                .referrer_path(referrer_path)
                .href(userTrackRequest.getHref())
                .host(userTrackRequest.getHost())
                .trackingId(trackingResult.getTrackingId())
                .newVisitor(trackingResult.getNewVisitor())
                .build();

        userTrackProducer.produce(userTrack);
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
