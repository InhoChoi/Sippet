package com.sippet.api;

import com.sippet.domain.usertrack.UserTrack;
import com.sippet.domain.usertrack.UserTrackRepository;
import com.sippet.domain.usertrack.UserTrackPathNameCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/track")
public class UserTrackController {
    @Autowired
    private UserTrackRepository userTrackRepository;

    @PostMapping(path = "/")
    public UserTrack addTrack(@RequestBody UserTrack userTrack) {
        return userTrackRepository.save(userTrack);
    }

    @GetMapping(path = "/group_by_count/path_name")
    public List<UserTrackPathNameCount> getPathNameCountList() {
        return userTrackRepository.findCountGroupByPathName();
    }
}
