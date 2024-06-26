package com.example.backend.service

import com.example.backend.domain.User
import com.example.backend.mvc.dto.input.RegisterUserDTO

interface UserService {

    fun getById(userId: Int) : User?
    fun getByEmail(email: String): User?
    fun create(registrationRequest: RegisterUserDTO) : User
}