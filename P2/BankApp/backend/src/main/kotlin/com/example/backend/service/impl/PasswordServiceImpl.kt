package com.example.backend.service.impl

import com.example.backend.service.PasswordService
import de.mkammerer.argon2.Argon2Factory
import org.springframework.stereotype.Service


@Service
class PasswordServiceImpl : PasswordService{
    private val argon2 =   Argon2Factory.create()
    override fun hashPassword(password: String): String {
        return argon2.hash(2, 65536, 1, password.toCharArray())
    }

    override fun verifyPassword(password: String, hash: String): Boolean {
        return argon2.verify(hash, password.toCharArray())
    }
}