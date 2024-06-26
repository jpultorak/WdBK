package com.example.backend.mvc.dto.input

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class RegisterUserDTO (
    @NotEmpty(message = "Firstname is mandatory")
    @NotNull(message = "Firstname is mandatory")
    val firstName: String,

    @NotEmpty(message = "Lastname is mandatory")
    @NotNull(message = "Lastname is mandatory")
    val lastName: String,

    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    val email: String,

    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    val password: String
)
