package com.example.backend.auth

import com.example.backend.domain.User
import com.example.backend.mvc.dto.input.AuthRequestDTO
import com.example.backend.mvc.dto.input.RegisterUserDTO
import com.example.backend.mvc.dto.output.AuthOutDTO
import com.example.backend.mvc.dto.output.UserOutDTO
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
class AuthController(private val service: AuthService) {


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun register(
        @RequestBody @Valid request: RegisterUserDTO
    ): ResponseEntity<UserOutDTO> {
        val userOutDTO : UserOutDTO = convertUserToDto(service.registerUser(request))
        return ResponseEntity.status(HttpStatus.CREATED).body(userOutDTO)
    }

    @PostMapping("/authenticate")
    fun authenticate(
        @RequestBody @Valid request: AuthRequestDTO
    ): ResponseEntity<AuthOutDTO> {
        return ResponseEntity.ok(service.authenticate(request))
    }

    private fun convertUserToDto(user : User) : UserOutDTO = UserOutDTO(
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        id = user.id,
        balance = user.balance,
    )
}