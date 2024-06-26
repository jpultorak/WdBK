package com.example.backend.dao

import com.example.backend.domain.Token
import com.example.backend.domain.User
import com.example.backend.domain.UserCreated
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import java.time.LocalDateTime

@Mapper
interface UserMapper {

    @Select(
        """
        SELECT id, first_name, last_name, email, password, balance
        FROM person
        WHERE person.id = #{id}
        """,
    )
    fun getUserById(@Param("id") userId : Int) : User?

    @Select(
        """
        SELECT id, first_name, last_name, email, password, balance
        FROM person
        WHERE person.email = #{email}
        """,
    )
    fun getUserByEmail(@Param("email") email : String) : User?

    @Select(
        """
        SELECT token, token_created_at, token_expires_at, token_validated_at
        FROM person
        WHERE person.email = #{email}
        """,
    )
    fun getTokenByEmail(@Param("email") email : String) : Token?

    @Update(
        """
        UPDATE person 
        WHERE person.email = #{email}
        SET token = #{token.token},
            token_created_at = #{token.createdAt}
            token_expires_at = #{token.expiresAt}
        """,
    )
    fun addToken(@Param("token") token: Token, @Param("email") email: String)

    @Update(
        """
        UPDATE person 
        WHERE person.email = #{email}
        SET token_validated_at = #{validationTime},
            enabled = true
        """,
    )
    fun setTokenValidated(@Param("validationTime") validationTime : LocalDateTime , @Param("email") email: String)
    @Select(
        """
        INSERT INTO person (first_name, last_name, email, password) 
        VALUES (#{firstName}, #{lastName}, #{email}, #{password}) 
        RETURNING id
        """
    )
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    fun insertUser(user: UserCreated): Int
}