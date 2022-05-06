package com.zxf.example.controllers;

import com.zxf.example.service.JWTIssueService;
import com.zxf.example.service.TargetURLChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class JWTIssueController {
    @Autowired
    private TargetURLChecker targetURLChecker;
    @Autowired
    private JWTIssueService jwtIssueService;

    @GetMapping("/jwt/issue")
    public ModelAndView issue(@RequestParam String userId, @RequestParam String orderId, @RequestParam String targetUrl) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, URISyntaxException {
        System.out.println("JWTIssueController::issue: " + userId + ", " + orderId + ", " + targetUrl);
        targetURLChecker.checkTargetUrl(targetUrl);
        String jwt = jwtIssueService.issue(userId, orderId, targetUrl, 30);
        ModelAndView modelAndView = new ModelAndView("jwt_submit");
        modelAndView.addObject("jwt", jwt);
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("targetUrl", targetUrl);
        return modelAndView;
    }

    @GetMapping("/jwt/issue-submit")
    public ModelAndView issueAndAutoSubmit(@RequestParam String userId, @RequestParam String orderId, @RequestParam String targetUrl) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, URISyntaxException {
        System.out.println("JWTIssueController::issueAndAutoSubmit: " + userId + ", " + orderId + ", " + targetUrl);
        targetURLChecker.checkTargetUrl(targetUrl);
        String jwt = jwtIssueService.issue(userId, orderId, targetUrl, 30);
        ModelAndView modelAndView = new ModelAndView("jwt_submit_auto");
        modelAndView.addObject("jwt", jwt);
        return modelAndView;
    }
}
