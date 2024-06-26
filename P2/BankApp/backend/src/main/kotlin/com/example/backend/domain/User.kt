package com.example.backend.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.math.BigDecimal
import java.security.Principal

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val balance: BigDecimal,
    val roles: List<Role>,
    val enabled: Boolean
) : UserDetails, Principal {
    override fun getAuthorities(): Collection<GrantedAuthority> {
       return this.roles.map { role -> SimpleGrantedAuthority(role.roleName) }
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
    fun fullName(): String{
        return "$firstName $lastName"
    }

    override fun getName(): String {
        return email
    }
}

