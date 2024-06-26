package com.example.backend.service.impl

import com.example.backend.dao.UserMapper
import com.example.backend.domain.User
import com.example.backend.domain.UserCreated
import com.example.backend.mvc.dto.input.RegisterUserDTO
import com.example.backend.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class UserServiceImpl(
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder,
    ) : UserService {

    @Transactional(readOnly = true)
    override fun getById(userId: Int): User? {
        return userMapper.getUserById(userId)
    }

    @Transactional(readOnly = true)
    override fun getByEmail(email: String): User? {
        return userMapper.getUserByEmail(email)
    }

    override fun create(registrationRequest: RegisterUserDTO): User {
        val newUser = UserCreated(
            firstName = registrationRequest.firstName,
            lastName = registrationRequest.lastName,
            email = registrationRequest.email,
            password = passwordEncoder.encode(registrationRequest.password),
        )
        val autoGeneratedId = userMapper.insertUser(newUser)
        return User(
            id = autoGeneratedId,
            firstName = newUser.firstName,
            lastName = newUser.lastName,
            email = newUser.email,
            userPassword = newUser.password,
            balance = BigDecimal(0.000)
        )
    }
}