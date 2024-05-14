package com.example.backend.mvc.dto.output

import java.math.BigDecimal

data class UserOutDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val balance: BigDecimal
)
