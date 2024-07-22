package com.example.backend.auth

import com.example.backend.domain.User
import com.example.backend.mvc.dto.input.AuthRequestDTO
import com.example.backend.mvc.dto.input.RegisterUserDTO
import com.example.backend.mvc.dto.output.AuthOutDTO
import com.example.backend.security.JwtService
import com.example.backend.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class AuthService(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
) {

    @Transactional
    fun registerUser(registrationRequest: RegisterUserDTO): User {
        return userService.create(registrationRequest)
    }
    fun authenticate(request: AuthRequestDTO): AuthOutDTO {
        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )
        val claims = HashMap<String, Any>()
        val user = auth.principal as User

        claims["fullName"] = user.fullName()
        val jwtToken = jwtService.generateToken(claims, auth.principal as User)
        return AuthOutDTO(jwtToken)
    }
}