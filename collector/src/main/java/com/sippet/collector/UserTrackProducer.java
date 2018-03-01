package com.sippet.collector;

import com.sippet.domain.domain.usertrack.UserTrackDto;
import com.sippet.domain.service.TrackingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
public class UserTrackProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public String divideReferrer(UserTrackRequest userTrackRequest) {
        String path = "";
        try {
            path =  new URI(userTrackRequest.getReferrer()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void produce(UserTrackRequest userTrackRequest, TrackingResult trackingResult) {
        String referrer_path = divideReferrer(userTrackRequest);
        String referrer_host = userTrackRequest.getReferrer()
                            .substring(0, userTrackRequest.getReferrer().length() - referrer_path.length());

        final UserTrackDto userTrackDto = UserTrackDto.builder()
                .pathName(userTrackRequest.getPathName())
                .referrer_host(referrer_host)
                .referrer_path(referrer_path)
                .href(userTrackRequest.getHref())
                .host(userTrackRequest.getHost())
                .trackingId(trackingResult.getTrackingId())
                .newVisitor(trackingResult.getNewVisitor())
                .build();

        amqpTemplate.convertAndSend(userTrackDto);
    }
}
