package com.example.backend.auth

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.mail.MessagingException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("auth")
@Tag(name = "Authentication")
class AuthController(private val service: AuthService) {


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun register(
        @RequestBody @Valid request: RegistrationRequest
    ): ResponseEntity<*> {
        service.registerUser(request)
        return ResponseEntity.accepted().build<Any>()
    }

    @PostMapping("/authenticate")
    fun authenticate(
        @RequestBody @Valid request: AuthRequest
    ): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(service.authenticate(request))
    }

    @GetMapping("/activate-account")
    @Throws(MessagingException::class)
    fun confirm(
        @RequestParam token: String?
    ) {
        service.activateAccount(token)
    }
}