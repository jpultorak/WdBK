package com.example.backend.dao

import com.example.backend.domain.User
import com.example.backend.domain.UserCreated
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

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
        INSERT INTO person (first_name, last_name, email, password) 
        VALUES (#{firstName}, #{lastName}, #{email}, #{password}) 
        RETURNING id
        """
    )
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    fun insertUser(user: UserCreated): Int
}