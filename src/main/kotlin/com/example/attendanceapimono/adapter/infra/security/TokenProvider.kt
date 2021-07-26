package com.example.attendanceapimono.adapter.infra.security

import com.example.attendanceapimono.util.log
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.stereotype.Component;
import java.security.Key
import java.util.*

@Component
class TokenProvider(val authConfig: AuthProperties) {
    val key: Key;

    init {
        this.key = Keys.hmacShaKeyFor(this.authConfig.secret.toByteArray());
    }

    fun createToken(userId: UUID): String {
        val now: Date = Date();
        val expiredDate: Date = Date(now.time + this.authConfig.expires)

        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(Date())
            .setExpiration(expiredDate)
            .signWith(this.key, SignatureAlgorithm.HS512)
            .compact();
    }

    fun getUserId(token: String): UUID {
        val claims: Claims  = Jwts
            .parserBuilder()
            .setSigningKey(this.key)
            .build()
            .parseClaimsJws(token)
            .body;

        return UUID.fromString(claims.subject);
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(authToken);
            return true;
        } catch (ex: SignatureException) {
            log.error("Invalid JWT signature");
        } catch (ex: MalformedJwtException) {
            log.error("Invalid JWT token");
        } catch (ex: ExpiredJwtException) {
            log.error("Expired JWT token");
        } catch (ex: UnsupportedJwtException) {
            log.error("Unsupported JWT token");
        } catch (ex: IllegalArgumentException) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}