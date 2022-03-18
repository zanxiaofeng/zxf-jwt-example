package com.zxf.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class TargetURLChecker {
    @Value("${target.urls}")
    private String targetUrls;

    public void checkTargetUrl(String targetUrl) {
        Boolean match = Pattern.compile(",").splitAsStream(targetUrls).anyMatch(targetUrl::equals);
        if (!match) {
            throw new RuntimeException("Not allowed target url");
        }
    }
}
