package com.example.backend.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${security.jwt.expiration-time}")
    private val jwtExpiration : Long){
    private val secretKey: SecretKey = Jwts.SIG.HS256.key().build()


    private fun generateJwt(userId : Int) : String {
        return Jwts.builder()
            .signWith(secretKey)
            .subject(userId.toString())
            .expiration(Date(System.currentTimeMillis() + jwtExpiration))
            .compact()
    }

    fun validateToken(token: String, userId: Int): Boolean {
        val extractedUserId = extractUserId(token)
        return userId == extractedUserId && !isTokenExpired(token)
    }

    private fun extractUserId(token: String): Int {
        return extractAllClaims(token).subject.toInt()  // Convert back to int
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date())
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload
    }
}