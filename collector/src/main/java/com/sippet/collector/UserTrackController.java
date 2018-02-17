package com.sippet.collector;

import com.sippet.domain.service.TrackingResolver;
import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackPathNameCount;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import com.sippet.domain.service.TrackingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/track")
public class UserTrackController {
//    @Autowired
//    private UserTrackRepository userTrackRepository;

    @Autowired
    private TrackingResolver trackingResolver;

    @Autowired
    private Producer producer;

    //@CrossOrigin(origins = "*")
    @PostMapping(path = "/")
    public void addTrack(@RequestBody UserTrackDto userTrackDto,
                         @CookieValue(value = "newVisitor", required = false) Boolean newVisistor,
                         @CookieValue(value = "trackingId", required = false) String trackingId,
                         HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
//        System.out.println("Cookies: " + httpServletRequest.getCookies()[0].getValue() + ", "
//                + httpServletRequest.getCookies()[1].getValue());

        System.out.println("collector test!");
        final TrackingResult trackingResult = trackingResolver.resolve(newVisistor, trackingId);
        bakeTrackingCookie(httpServletResponse, trackingResult);

        //직렬화하거나 하나의 object에 담아서 send 해야됨.
        final UserTrack userTrack = UserTrack.builder()
                .pathName(userTrackDto.getPathName())
                .referrer(userTrackDto.getReferrer())
                .href(userTrackDto.getHref())
                .host(userTrackDto.getHost())
                .trackingId(trackingResult.getTrackingId())
                .newVisitor(trackingResult.getNewVisitor())
                .build();

        //TODO. producer.sendTo(); 이게 rabbitmq producer의 로직이 얽힌 매서드다.
        //producer.sendTo(MQProducerConfiguration.queueName, userTrack);
        producer.sendTo(MQProducerConfiguration.queueName, "Test it.");
        // TODO: 2018. 2. 4. conusmer로 로직을 옮겨야 됨
        //userTrackRepository.save(userTrack);
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
