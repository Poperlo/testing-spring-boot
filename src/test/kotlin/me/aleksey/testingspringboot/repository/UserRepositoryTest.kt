package me.aleksey.testingspringboot.repository

import me.aleksey.testingspringboot.model.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.junit.jupiter.Testcontainers


@DataJpaTest
@Testcontainers
internal class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun init() {
        userRepository.save(User("Aleksey"))
    }

    @Test
    @Sql(scripts = ["/data.sql"])
    fun `should retrieve users count when called`() {
        val result = userRepository.getUsersCount()

        Assertions.assertThat(result).isEqualTo(4)
    }

    @Test
    fun `should retrieve zero users count when called`() {
        val result = userRepository.getUsersCount()

        Assertions.assertThat(result).isEqualTo(1)
    }
}
