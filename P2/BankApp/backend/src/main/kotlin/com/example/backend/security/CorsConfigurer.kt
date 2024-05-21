package com.example.backend.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfigurer( @Value("\${cors.allowed-origins}")
                      private val allowedOrigins: String) : WebMvcConfigurer {

    @Override
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(allowedOrigins)
            .allowCredentials(true)
            .allowedHeaders("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
    }

}