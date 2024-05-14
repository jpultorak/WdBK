package com.example.backend.domain

import java.math.BigDecimal

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val balance: BigDecimal
)

