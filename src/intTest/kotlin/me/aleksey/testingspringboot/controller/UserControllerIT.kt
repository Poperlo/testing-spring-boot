package me.aleksey.testingspringboot.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import me.aleksey.testingspringboot.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserControllerIT {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `should return error when called`(applicationContext: ConfigurableApplicationContext) {
        every { userRepository.findAll() } throws Exception("Something went wrong")

        webTestClient.get()
            .uri("/users")
            .exchange()
            .expectStatus().is5xxServerError
    }
}
