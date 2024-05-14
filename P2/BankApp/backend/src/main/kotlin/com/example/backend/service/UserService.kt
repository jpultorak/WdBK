package com.example.backend.service

import com.example.backend.domain.User

interface UserService {

    fun getUserById(userId: Int) : User
}