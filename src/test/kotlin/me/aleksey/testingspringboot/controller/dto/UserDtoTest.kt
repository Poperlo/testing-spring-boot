package me.aleksey.testingspringboot.controller.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester

@JsonTest
internal class UserDtoTest {

    @Autowired
    private lateinit var jacksonTester: JacksonTester<UserDto>

    @Test
    fun `should properly convert entity to dto when called`() {
        val userDto = UserDto(null, "Aleksey")

        val result = jacksonTester.write(userDto)

        assertThat(result)
            .doesNotHaveJsonPath("$.id")
            .hasJsonPathStringValue("$.username")
            .extractingJsonPathStringValue("$.username").isEqualTo("Aleksey")
    }
}