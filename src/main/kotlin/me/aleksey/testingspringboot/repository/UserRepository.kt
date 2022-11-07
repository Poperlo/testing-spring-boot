package me.aleksey.testingspringboot.repository

import me.aleksey.testingspringboot.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    @Query("select count(u) from User u")
    fun getUsersCount(): Int
}
