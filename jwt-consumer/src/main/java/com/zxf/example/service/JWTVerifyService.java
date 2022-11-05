package com.zxf.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


@Service
public class JWTVerifyService {
    public DecodedJWT verify(String jwtToken) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, URISyntaxException {
        Algorithm rs256ForVerify = Algorithm.RSA256(loadPublicKey(), null);
        DecodedJWT verifiedJWT = JWT.require(rs256ForVerify)
                .withIssuer("ZXF")
                .build()
                .verify(jwtToken);
        return verifiedJWT;
    }

    private RSAPublicKey loadPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, URISyntaxException {
        URL publicKeyURL = this.getClass().getClassLoader().getResource("keys/public-key");
        // Please note "Files.readAllBytes(Paths.get(publicKeyURL.toURI()))" will fail when run this program by jar
        byte[] encodedPublicKey = Files.readAllBytes(Paths.get(publicKeyURL.toURI()));
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(encodedPublicKey));
    }
}
