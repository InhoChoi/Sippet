package com.sippet.service;

import com.sippet.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private UserTrackRepository userTrackRepository;


}
