package me.aleksey.testingspringboot.controller

import me.aleksey.testingspringboot.controller.dto.UserDto
import me.aleksey.testingspringboot.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAll(principal: Principal) : List<UserDto> = listOf(UserDto(0, principal.name))

    @GetMapping("/secured")
    fun getAllSecured() : List<UserDto> = userService.getAllUsers()
}
