package com.example.backend.service

import com.example.backend.domain.User
import com.example.backend.mvc.dto.input.CreateUserDTO
import com.example.backend.mvc.dto.input.UpdatePasswordDTO

interface UserService {

    fun getById(userId: Int) : User
    fun getByEmail(email: String): User
    fun createUser(createUserDTO : CreateUserDTO) : User

    fun updatePassword(updatePasswordDTO: UpdatePasswordDTO, userId: Int)
}