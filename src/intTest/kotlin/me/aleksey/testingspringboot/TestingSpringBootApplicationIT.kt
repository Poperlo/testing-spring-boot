package me.aleksey.testingspringboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class TestingSpringBootApplicationIT {

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun contextLoads() {
        assertThat(testRestTemplate).isNotNull
    }

    @Test
    fun `should return all users when requested`() {
        testRestTemplate.getForEntity("/users", String::class.java)
    }
}