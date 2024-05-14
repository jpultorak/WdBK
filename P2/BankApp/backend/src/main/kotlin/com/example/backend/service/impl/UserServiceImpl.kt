package com.example.backend.service.impl

import com.example.backend.dao.UserMapper
import com.example.backend.domain.User
import com.example.backend.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userMapper: UserMapper) : UserService {

    @Transactional(readOnly = true)
    override fun getUserById(userId: Int): User {
        return userMapper.getUserById(userId)
    }
}