package com.example.attendanceapimono.adapter.infra.security

import com.example.attendanceapimono.constant.SecurityConst
import com.example.attendanceapimono.util.log
import com.example.attendanceapimono.util.tryOrNull
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component;
import java.security.Key
import java.util.*

@Component
class TokenProvider(val authConfig: AuthProperties) {
    val key: Key;

    init {
        this.key = Keys.hmacShaKeyFor(this.authConfig.secret.toByteArray());
    }

    private fun getClaims(token: String) = Jwts
        .parserBuilder()
        .setSigningKey(this.key)
        .build()
        .parseClaimsJws(token)
        .body;

    fun createToken(userDetails: UserDetails): String {
        val now: Date = Date();
        val expiredDate: Date = Date(now.time + this.authConfig.expires)

        return Jwts.builder()
            .setSubject(userDetails.username)
            .addClaims(mapOf(SecurityConst.CLAIMS_ROLE to userDetails.authorities))
            .setIssuer(SecurityConst.ADDRESS_URL)
            .setIssuedAt(Date())
            .setExpiration(expiredDate)
            .signWith(this.key, SignatureAlgorithm.HS512)
            .compact();
    }

    fun getUserId(token: String): UUID = UUID.fromString(this.getClaims(token).subject);

    fun getAuthentication(token: String): Authentication? = tryOrNull {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

//        if (claims[CLAIMS_TOKEN_TYPE] != ACCESS_TOKEN_TYPE) {
//            return null
//        }

        val authorities = (claims[SecurityConst.CLAIMS_ROLE] as ArrayList<*>).map { SimpleGrantedAuthority(it as String) }

        return UsernamePasswordAuthenticationToken(
            User(claims.subject, "", authorities),
            token,
            authorities
        )
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