package com.example.backend.mvc.controller

import com.example.backend.domain.User
import com.example.backend.mvc.dto.output.UserOutDTO
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/get-user-info")
    fun helloWorld(): ResponseEntity<UserOutDTO> {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as User
        return ResponseEntity.ok(convertUserToDto(userDetails))
    }

    private fun convertUserToDto(user : User) : UserOutDTO = UserOutDTO(
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        id = user.id,
        balance = user.balance
    )
}