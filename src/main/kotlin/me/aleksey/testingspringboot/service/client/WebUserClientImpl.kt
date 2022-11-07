package me.aleksey.testingspringboot.service.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WebUserClientImpl(
    @Value("\${clients.web-user.base-url}") baseUrl: String
) : WebUserClient {

    private val client = WebClient.builder()
        .baseUrl("$baseUrl/api/users")
        .build()

    override fun getWebUser(id: Int): WebUser? =
        client.get()
            .uri("/{id}", id)
            .retrieve()
            .bodyToMono(WebUser::class.java)
            .block()
}
