package com.example.backend.domain

import java.time.LocalDateTime

data class Token(
    val id : String,
    val token: String,
    val createdAt: LocalDateTime,
    val validatedAt: LocalDateTime,
    val expiresAt: LocalDateTime,
)
