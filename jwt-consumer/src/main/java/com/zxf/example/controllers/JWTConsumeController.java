package com.zxf.example.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxf.example.service.JWTTokenStore;
import com.zxf.example.service.JWTVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class JWTConsumeController {
    @Autowired
    private JWTVerifyService jwtVerifyService;
    @Autowired
    private JWTTokenStore jwtTokenStore;

    @PostMapping("/jwt/consume/gateway")
    public ModelAndView gateway(HttpServletRequest httpRequest, HttpServletResponse httpResponse, @ModelAttribute ConsumeRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, URISyntaxException {
        System.out.println("JWTConsumeController::gateway: " + request.getJwt());
        DecodedJWT verifiedJwt = jwtVerifyService.verify(request.getJwt());
        jwtTokenStore.saveJWTToken(httpRequest, httpResponse, verifiedJwt);
        return new ModelAndView("redirect:" + verifiedJwt.getClaim("targetUrl").asString());
    }

    @GetMapping("/jwt/consume/business/one")
    public ModelAndView businessOne(HttpServletRequest httpRequest) {
        System.out.println("JWTConsumeController::businessOne");
        DecodedJWT verifiedJwt = jwtTokenStore.loadJWToken(httpRequest);
        ModelAndView modelAndView = new ModelAndView("business_one");
        modelAndView.addObject("result", verifiedJwt);
        return modelAndView;
    }

    @GetMapping("/jwt/consume/business/two")
    public ModelAndView businessTwo(HttpServletRequest httpRequest) {
        System.out.println("JWTConsumeController::businessTwo");
        DecodedJWT verifiedJwt = jwtTokenStore.loadJWToken(httpRequest);
        ModelAndView modelAndView = new ModelAndView("business_two");
        modelAndView.addObject("result", verifiedJwt);
        return modelAndView;
    }

    @GetMapping("/jwt/consume/close")
    public ModelAndView close(HttpServletRequest httpRequest) {
        System.out.println("JWTConsumeController::close");
        jwtTokenStore.removeJWToken(httpRequest);
        SecurityContextHolder.getContext().setAuthentication(null);
        ModelAndView modelAndView = new ModelAndView("close");
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
