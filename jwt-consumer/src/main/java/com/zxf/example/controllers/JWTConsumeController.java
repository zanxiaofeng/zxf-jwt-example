package com.zxf.example.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxf.example.service.JWTVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class JWTConsumeController {
    @Autowired
    private JWTVerifyService jwtVerifyService;

    @PostMapping("/jwt/consume")
    public ModelAndView consume(HttpServletRequest httpRequest, @ModelAttribute ConsumeRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, URISyntaxException {
        System.out.println("JWTConsumeController::consume: " + request.getJwt());
        DecodedJWT verifiedJwt = jwtVerifyService.verify(request.getJwt());
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("verifiedJwt", verifiedJwt);
        ModelAndView modelAndView = new ModelAndView("consume");
        modelAndView.addObject("result", verifiedJwt);
        return modelAndView;
    }

    @GetMapping("/jwt/consume/result")
    public ModelAndView consumeResult(HttpServletRequest httpRequest) {
        System.out.println("JWTConsumeController::consume.result");
        HttpSession session = httpRequest.getSession(false);
        ModelAndView modelAndView = new ModelAndView("consume_result");
        modelAndView.addObject("result", session.getAttribute("verifiedJwt"));
        return modelAndView;
    }

    @GetMapping("/jwt/consume/close")
    public ModelAndView consumeClose(HttpServletRequest httpRequest) {
        System.out.println("JWTConsumeController::consume.close");
        HttpSession session = httpRequest.getSession(false);
        session.invalidate();
        SecurityContextHolder.getContext().setAuthentication(null);
        ModelAndView modelAndView = new ModelAndView("consume_close");
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
