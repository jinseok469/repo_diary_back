package com.diary.module.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	
	

	
		@Value("${jwt_secret_key}")
	    private  String SECRET_KEY ;

		private  SecretKey key ;
		
		// 빈 생성 후 @Value 주입된 후 초기화하기 위해 @PostConstruct 사용
	    @PostConstruct
	    public void init() {
	        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	    }

	    // 토큰 생성
	    public String generateToken(String id,String name) {
	        return Jwts.builder()
	                .setSubject(id)
	                .claim("name",name)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120)) // 2시간
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	    }

	    // 토큰에서 사용자 이름 추출
	    public String extractUsername(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }

	    // 토큰 유효성 검사
	    public boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder()
	                    .setSigningKey(key)
	                    .build()
	                    .parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            return false; // 토큰 유효하지 않음
	        }
	    }
	}

	

