package com.example.backend.mvc.dto.input

data class CreateUserDTO(
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String
)
