package com.example.backend.mvc.controller

import com.example.backend.domain.User
import com.example.backend.mvc.dto.output.UserOutDTO
import com.example.backend.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(private val userService : UserService) {

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable userId : Int) : UserOutDTO{
        return convertUserToDto(userService.getUserById(userId))
    }

    private fun convertUserToDto(user : User) : UserOutDTO = UserOutDTO(
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        balance = user.balance,
    )
}