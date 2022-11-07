package me.aleksey.testingspringboot.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import me.aleksey.testingspringboot.controller.dto.UserDto
import me.aleksey.testingspringboot.service.UserService
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(UserController::class)
internal class UserControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    private lateinit var userServiceMock: UserService

    @Test
    fun `should retrieve all users`() {
        every { userServiceMock.getAllUsers() } returns listOf(
            UserDto(1, "Aleksey"),
            UserDto(2, "Dmitriy"),
            UserDto(3, "Irina"),
            UserDto(4, "Sergey")
        )

        mockMvc.get("/users") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.length()", equalTo(4))
            jsonPath("$[0].username", equalTo("Aleksey"))
        }
    }

    @Test
    @Disabled
    fun `should fail on retrieve all users when not authenticated`() {
        mockMvc.get("/users/secured") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isUnauthorized() }
        }
    }

    @Test
    @WithMockUser(username = "aleksey")
    fun `should success on retrieve all users when authenticated`() {
        every { userServiceMock.getAllUsers() } returns emptyList()

        mockMvc.get("/users/secured") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }
}
