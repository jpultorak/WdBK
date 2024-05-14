package com.example.backend.dao

import com.example.backend.domain.User
import com.example.backend.domain.UserCreated
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface UserMapper {

    @Select("SELECT first_name, last_name, email, balance" +
            "FROM person" +
            "WHERE person.id = #{id}")
    fun getUserById(@Param("id") userId : Int) : User

    @Select("INSERT INTO person (first_name, last_name, email, password) VALUES (#{firstName}, #{lastName}, #{email}, #{password}) RETURNING id")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    fun insertUser(user: UserCreated): Int

    @Update("UPDATE person" +
            "WHERE id = #{id}" +
            "SET password = #{newPassword}")
    fun updatePassword(@Param("id") userId: Int, newPassword: String)
}