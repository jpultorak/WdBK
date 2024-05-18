package com.example.backend.mvc.controller

import com.example.backend.domain.User
import com.example.backend.mvc.dto.input.CreateUserDTO
import com.example.backend.mvc.dto.input.UpdatePasswordDTO
import com.example.backend.mvc.dto.output.UserOutDTO
import com.example.backend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(private val userService : UserService) {

    @GetMapping("/users/{userId}")
    fun getUser(@PathVariable userId : Int) : ResponseEntity<UserOutDTO>{
        val userDTO = convertUserToDto(userService.getUserById(userId))
        return ResponseEntity.ok(userDTO)
    }

    @PostMapping("/users")
    fun createUser(@RequestBody createUserDTO: CreateUserDTO) : ResponseEntity<UserOutDTO>{
        val user = convertUserToDto(userService.createUser(createUserDTO))
        return ResponseEntity.ok(user)
    }

    @PatchMapping("/reset-password/{userId}")
    fun updatePassword(@RequestBody updatePasswordDTO: UpdatePasswordDTO, @PathVariable userId: Int): ResponseEntity<Unit> {
        userService.updatePassword(updatePasswordDTO, userId)
        return ResponseEntity.noContent().build()
    }

    private fun convertUserToDto(user : User) : UserOutDTO = UserOutDTO(
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        password = user.password,
        balance = user.balance,
        id = user.id
    )
}