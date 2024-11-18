package cn.edu.nbut.chatroom;

import cn.edu.nbut.chatroom.model.User;
import cn.edu.nbut.chatroom.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Date;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JwtTests {

    private SecretKey key = JwtUtil.getSecretKey();


    public String jwtCreateTest() {
        User testUser = new User("123456", "test");
        String jwt = JwtUtil.createToken(
                testUser,
                new Date(System.currentTimeMillis() + 1000 * 600));
        System.out.println(jwt);
        return jwt;
    }

    public void jwtParseTest() {
        String jwt = jwtCreateTest();

        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt);

            System.out.println(jws.getPayload().get("name"));
        } catch (JwtException e) {
            e.printStackTrace();
        }

    }

}
