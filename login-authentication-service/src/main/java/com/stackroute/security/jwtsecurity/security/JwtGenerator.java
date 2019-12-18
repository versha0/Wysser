package com.stackroute.security.jwtsecurity.security;

import com.stackroute.security.jwtsecurity.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JwtGenerator {


    public String generate(User user) {


        Claims claims = Jwts.claims();

        claims.setSubject(user.getEmail()); // claims.put("sub",retailer.getEmail());
//        claims.put("pass", user.getPassword());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("role", user.getRole());

        claims.setExpiration(new Date(System.currentTimeMillis()+ 3600000));

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "stackroute").compact();
    }
}
