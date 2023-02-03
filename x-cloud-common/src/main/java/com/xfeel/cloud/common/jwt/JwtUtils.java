package com.xfeel.cloud.common.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {

    private static final Long JWT_TTL = 60 * 60 * 1000L;
    private static final String JWT_KEY = "jwt_ready_2_go";
    private static final String JWT_ISSUER = "x";

    public static String creatJwt(String subject) {
        return createJwt(getUUID(), subject, null);
    }

    public static String createJwt(String subject, Long ttlMillis) {
        return createJwt(getUUID(), subject, ttlMillis);
    }

    public static String createJwt(String id, String subject, Long ttlMillis) {
        return getJwtBuilder(subject, ttlMillis, id).compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = createSecretKey();
        long nowMillis = System.currentTimeMillis();
        if (ttlMillis == null) {
            ttlMillis = JwtUtils.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        return Jwts.builder().setId(uuid).setSubject(subject).setIssuer(JWT_ISSUER).setIssuedAt(new Date(nowMillis))
                .signWith(secretKey, signatureAlgorithm)
                .setExpiration(new Date(expMillis));
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static SecretKey createSecretKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }

    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = createSecretKey();
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJwt(jwt)
                .getBody();
    }
}
