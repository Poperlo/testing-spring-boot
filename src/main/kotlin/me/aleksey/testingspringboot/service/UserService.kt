package me.aleksey.testingspringboot.service

import me.aleksey.testingspringboot.controller.dto.UserDto
import me.aleksey.testingspringboot.model.User
import me.aleksey.testingspringboot.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<UserDto> = userRepository.findAll().map(User::toUserDto)
}

private fun User.toUserDto() = UserDto(id, name)