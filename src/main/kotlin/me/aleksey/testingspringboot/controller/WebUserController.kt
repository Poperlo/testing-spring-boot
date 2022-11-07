package me.aleksey.testingspringboot.controller

import me.aleksey.testingspringboot.controller.dto.WebUserDto
import me.aleksey.testingspringboot.service.WebUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/web-users")
class WebUserController(
    private val webUserService: WebUserService
) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Int): WebUserDto = webUserService.getWebUser(id)
}
