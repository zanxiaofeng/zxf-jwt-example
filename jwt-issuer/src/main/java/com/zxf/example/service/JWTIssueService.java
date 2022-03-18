package com.zxf.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

@Service
public class JWTIssueService {

    public String issue(String userId, String orderId, String targetUrl, Integer expiredSeconds) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, URISyntaxException {
        Date now = new Date(System.currentTimeMillis());
        Date expired = new Date(System.currentTimeMillis() + expiredSeconds * 1000);
        Algorithm rs256ForSign = Algorithm.RSA256(null, loadPrivateKey());

        String jwtToken = JWT.create()
                .withIssuer("ZXF")
                .withSubject("JWT Issuer Test")
                .withIssuedAt(now)
                .withNotBefore(now)
                .withExpiresAt(expired)
                .withClaim("userId", userId)
                .withClaim("orderId", orderId)
                .withClaim("targetUrl", targetUrl)
                .sign(rs256ForSign);
        return jwtToken;
    }

    private RSAPrivateKey loadPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, URISyntaxException {
        URL privateKeyURL = this.getClass().getClassLoader().getResource("keys/private-key");
        byte[] encodedPrivateKey = Files.readAllBytes(Paths.get(privateKeyURL.toURI()));
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));
    }
}
