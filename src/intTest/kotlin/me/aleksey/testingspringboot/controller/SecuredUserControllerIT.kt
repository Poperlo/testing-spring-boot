package me.aleksey.testingspringboot.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import me.aleksey.testingspringboot.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = [
    "spring.security.user.name=aleksey",
    "spring.security.user.password=password"
])
internal class SecuredUserControllerIT {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `should return error when called`(applicationContext: ConfigurableApplicationContext) {
        every { userRepository.findAll() } throws Exception("Something went wrong")

        webTestClient.get()
            .uri("/users/secured")
            .headers { headers ->
                headers.setBasicAuth("aleksey", "password")
            }
            .exchange()
            .expectStatus().is5xxServerError
    }
}
