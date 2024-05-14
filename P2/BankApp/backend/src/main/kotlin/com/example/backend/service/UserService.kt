package com.example.backend.service

import com.example.backend.domain.User
import com.example.backend.mvc.dto.input.CreateUserDTO
import com.example.backend.mvc.dto.input.UpdatePasswordDTO

interface UserService {

    fun getUserById(userId: Int) : User
    fun createUser(createUserDTO : CreateUserDTO) : User

    fun updatePassword(updatePasswordDTO: UpdatePasswordDTO, userId: Int)
}