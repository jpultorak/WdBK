package com.example.backend.dao

import com.example.backend.domain.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {

    @Select("Select first_name, last_name, email, balance" +
            "FROM person" +
            "WHERE person.id = #{id}")
    fun getUserById(@Param("id") id : Int) : User
}