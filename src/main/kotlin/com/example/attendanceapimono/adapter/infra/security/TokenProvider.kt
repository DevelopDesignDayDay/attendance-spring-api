package com.example.attendanceapimono.adapter.infra.security

import com.example.attendanceapimono.util.tryOrNull
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class TokenProvider(authConfig: AuthProperties) {
    private val key: Key = Keys.hmacShaKeyFor(authConfig.secret.toByteArray())
    private val expires = authConfig.expires

    private fun getClaims(token: String) = Jwts
        .parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .body

    fun createToken(userDetails: UserDetails): String {
        val now = Date()
        val expiredDate = Date(now.time + expires)

        return Jwts.builder()
            .setSubject(userDetails.username)
            .addClaims(mapOf(SecurityConst.CLAIMS_ROLE to userDetails.authorities))
            .setIssuer(SecurityConst.ADDRESS_URL)
            .setIssuedAt(now)
            .setExpiration(expiredDate)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }

    //TODO 사용하는건가ㅣ
    fun getUserId(token: String): UUID = UUID.fromString(getClaims(token).subject)

    fun getAuthentication(token: String) = tryOrNull<Authentication> {
        val claims = getClaims(token)

        val roles = (claims[SecurityConst.CLAIMS_ROLE] as ArrayList<*>)
            .mapNotNull { RoleAdapter.valueOf(it as String) }

        return UsernamePasswordAuthenticationToken(
            UserPrincipal(UUID.fromString(claims.subject), roles),
            token,
            roles
        )
    }
}