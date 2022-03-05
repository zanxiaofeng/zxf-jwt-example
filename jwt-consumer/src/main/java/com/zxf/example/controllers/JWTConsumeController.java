package com.zxf.example.controllers;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxf.example.service.JWTVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class JWTConsumeController {
    @Autowired
    private JWTVerifyService jwtVerifyService;

    @PostMapping("/jwt/consume")
    public ModelAndView consume(@ModelAttribute ConsumeRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, URISyntaxException {
        System.out.println("JWTConsumeController::consume: " + request.getJwt());
        DecodedJWT verifiedJwt = jwtVerifyService.verify(request.getJwt());
        ModelAndView modelAndView = new ModelAndView("consume");
        modelAndView.addObject("result", verifiedJwt);
        return modelAndView;
    }

    @GetMapping("/jwt/consumed")
    public ModelAndView consumed() {
        System.out.println("JWTConsumeController::consumed");
        ModelAndView modelAndView = new ModelAndView("consumed");
        return modelAndView;
    }

    public static class ConsumeRequest {
        private String jwt;

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }
    }
}
