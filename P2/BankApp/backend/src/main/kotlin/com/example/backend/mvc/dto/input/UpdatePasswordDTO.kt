package com.example.backend.mvc.dto.input

data class UpdatePasswordDTO (
    val newPassword: String,
    val oldPassword: String,
)