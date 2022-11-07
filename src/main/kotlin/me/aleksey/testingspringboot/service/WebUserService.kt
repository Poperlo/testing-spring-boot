package me.aleksey.testingspringboot.service

import me.aleksey.testingspringboot.controller.dto.WebUserDto
import me.aleksey.testingspringboot.service.client.WebUser
import me.aleksey.testingspringboot.service.client.WebUserClient
import org.springframework.stereotype.Service

@Service
class WebUserService(
    private val webUserClient: WebUserClient
) {

    fun getWebUser(id: Int): WebUserDto = webUserClient.getWebUser(id)?.toWebUserDto()
        ?: throw IllegalStateException("Not found user with such ID")
}

private fun WebUser.toWebUserDto() = WebUserDto(data.id, "${data.firstName} ${data.lastName}")