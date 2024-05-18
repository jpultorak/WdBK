package com.example.backend.service

interface PasswordService {

    fun hashPassword(password: String) : String
    fun verifyPassword(password: String, hash: String) :Boolean
}