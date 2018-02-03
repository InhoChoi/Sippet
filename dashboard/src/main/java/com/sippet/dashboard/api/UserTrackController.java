package com.sippet.dashboard.api;

import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackPathNameCount;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import com.sippet.domain.service.TrackingResolver;
import com.sippet.domain.service.TrackingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/api/track")
public class UserTrackController {
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

        //TODO. 이 부분에서 받ㅇㄴ 정보를 바탕으로 전달해야 함 -> MQ의 Producer 부분에 전달하고 거기에서 Consumer로 다시 전송해서 거기에서 저장.
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
        return userTrackRepository.findCountGroupByPathName();
    }

    @GetMapping(path = "/count/visitor")
    public VisitorCount getNewVisitorCount() {
        return VisitorCount.create(
                userTrackRepository.findCountTotalVisitor(),
                userTrackRepository.findCountNewVisitor()
        );
    }
}
