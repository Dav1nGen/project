package cn.edu.nbut.chatroom.utils;

import cn.edu.nbut.chatroom.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
public class JwtUtil {
    private static SecretKey secretKey = Jwts.SIG.HS256.key().build();

    /**
     * 生成token
     * @param user 用户
     * @param expiration 过期时间
     * @return String
     */
    public static String createToken(User user, Date expiration) {

        String jwt = Jwts.builder()
                .signWith(secretKey)
                .claims(user.toMap())
                .setExpiration(expiration)
                .compact();
        return jwt;
    }

    public static Claims parseToken(String token) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            return jws.getPayload();
        } catch (JwtException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static SecretKey getSecretKey() {
        return secretKey;
    }
}
