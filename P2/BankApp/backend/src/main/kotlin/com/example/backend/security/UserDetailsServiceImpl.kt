package com.example.backend.security

import com.example.backend.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userService: UserService ) : UserDetailsService {

    override fun loadUserByUsername(userEmail: String): UserDetails {
        return userService.getByEmail(userEmail) ?: throw UsernameNotFoundException("User with email $userEmail not found")
    }
}