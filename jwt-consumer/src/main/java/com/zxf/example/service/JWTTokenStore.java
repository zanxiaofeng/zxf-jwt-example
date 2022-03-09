package com.zxf.example.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.UUID;

@Service
public class JWTTokenStore {
    @Value("${session.use}")
    Boolean sessionUse;
    @Autowired
    private Cache consumeCache;

    public void saveJWTToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse, DecodedJWT verifiedJwt) {
        if (sessionUse) {
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("verifiedJwt", verifiedJwt);
        } else {
            String mySessionId = genAndSaveMySessionId(httpResponse);
            consumeCache.put(new Element(mySessionId + "_verifiedJwt", verifiedJwt));
        }
    }

    public DecodedJWT loadJWToken(HttpServletRequest httpRequest) {
        if (sessionUse) {
            HttpSession session = httpRequest.getSession(false);
            return (DecodedJWT) session.getAttribute("verifiedJwt");
        } else {
            String mySessionId = getMySessionId(httpRequest);
            Element element = consumeCache.get(mySessionId + "_verifiedJwt");
            if (element == null) {
                return null;
            }
            return (DecodedJWT) element.getObjectValue();
        }
    }

    public void removeJWToken(HttpServletRequest httpRequest) {
        if (sessionUse) {
            HttpSession session = httpRequest.getSession(false);
            session.invalidate();
        } else {
            String mySessionId = getMySessionId(httpRequest);
            consumeCache.remove(mySessionId + "_verifiedJwt");
        }
    }

    private String genAndSaveMySessionId(HttpServletResponse response) {
        String mySessionId = UUID.randomUUID().toString();
        response.addCookie(new Cookie("MY_SESSION_ID", mySessionId));
        return mySessionId;
    }

    private String getMySessionId(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("MY_SESSION_ID"))
                .findFirst()
                .map(Cookie::getValue).orElse("");
    }
}
