package com.example.backend.domain

data class UserCreated(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

