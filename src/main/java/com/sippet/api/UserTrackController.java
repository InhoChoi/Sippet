package com.sippet.api;

import com.sippet.domain.usertrack.UserTrack;
import com.sippet.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private UserTrackRepository userTrackRepository;

    @PostMapping(path = "/")
    public UserTrack addTrack(@RequestBody UserTrack userTrack) {
        return userTrackRepository.save(userTrack);
    }

    @GetMapping(path = "/all")
    public Iterable<UserTrack> getAll() {
        return userTrackRepository.findAll();
    }
}
