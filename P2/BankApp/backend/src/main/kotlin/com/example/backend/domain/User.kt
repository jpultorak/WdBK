package com.example.backend.domain

import java.math.BigDecimal

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val balance: BigDecimal
)

