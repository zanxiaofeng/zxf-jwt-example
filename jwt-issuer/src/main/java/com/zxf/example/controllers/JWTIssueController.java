package com.zxf.example.controllers;

import com.zxf.example.service.JWTIssueService;
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
    private JWTIssueService jwtIssueService;

    @GetMapping("/jwt/issue")
    public ModelAndView issue(@RequestParam String businessId, @RequestParam String targetUrl) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, URISyntaxException {
        System.out.println("JWTIssueController::issue: " + businessId + ", " + targetUrl);
        String jwt = jwtIssueService.issue(businessId, targetUrl, 30);
        ModelAndView modelAndView = new ModelAndView("jwt_submit");
        modelAndView.addObject("jwt", jwt);
        modelAndView.addObject("businessId", businessId);
        modelAndView.addObject("targetUrl", targetUrl);
        return modelAndView;
    }
}
