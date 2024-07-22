package com.example.backend.domain

import java.math.BigDecimal

data class UserCreated(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)

