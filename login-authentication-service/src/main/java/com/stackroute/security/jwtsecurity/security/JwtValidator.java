package com.stackroute.security.jwtsecurity.security;

import com.stackroute.security.jwtsecurity.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "stackroute";

    public User validate(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();

            user.setEmail(body.getSubject());
//            user.setPass((String) body.get("pass"));
            user.setId(Long.parseLong((String) body.get("userId")));
            user.setRole((String) body.get("role"));
        } catch (Exception e) {
            System.out.println("token validation exception is = "+e);
        }

        return user;
    }
}
