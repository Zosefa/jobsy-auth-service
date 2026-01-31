package com.auth.auth_service.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth.auth_service.dto.AuthResponse;
import com.auth.auth_service.entity.Utilisateur;
import com.auth.auth_service.exception.BusinessException;
import com.auth.auth_service.repository.UtilisateurRepository;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.access-secret}")
    private String accessSecret;

    @Value("${jwt.refresh-secret}")
    private String refreshSecret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private final UtilisateurRepository utilisateurRepository;

    private Key getSigningKeyAccess() {
        return Keys.hmacShaKeyFor(accessSecret.getBytes(StandardCharsets.UTF_8));
    }

    private Key getSigningKeyRefresh() {
        return Keys.hmacShaKeyFor(refreshSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Utilisateur user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSigningKeyAccess(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Utilisateur user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKeyRefresh(), SignatureAlgorithm.HS256)
                .compact();
    }

    public AuthResponse generateTokens(Utilisateur user) {
        return new AuthResponse(
                generateAccessToken(user),
                generateRefreshToken(user)
        );
    }

    public String extractUsernameFromAccess(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKeyAccess())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractUsernameFromRefresh(String refresh) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKeyRefresh())
                .build()
                .parseClaimsJws(refresh)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            extractUsernameFromAccess(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public boolean isRefreshValid(String refresh) {
        try {
            extractUsernameFromRefresh(refresh);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public AuthResponse refresh(String refreshToken) {
        if (!isRefreshValid(refreshToken)) {
            throw new BusinessException("Refresh token invalide");
        }

        String email = extractUsernameFromRefresh(refreshToken);
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Utilisateur introuvable"));

        return generateTokens(user);
    }
}
