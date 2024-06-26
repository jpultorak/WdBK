package com.example.backend.domain

import java.time.LocalDateTime

data class Token(
    val token: String,
    val createdAt: LocalDateTime,
    val validatedAt: LocalDateTime?,
    val expiresAt: LocalDateTime,
)
