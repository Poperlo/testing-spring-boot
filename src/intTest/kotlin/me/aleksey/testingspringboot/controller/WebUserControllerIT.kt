package me.aleksey.testingspringboot.controller

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.junit5.WireMockExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class WebUserControllerIT {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `should return user when called`(applicationContext: ConfigurableApplicationContext) {
        wireMockServer.stubFor(
            WireMock.get("/api/users/1")
                .willReturn(
                    WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                            """
                            {
                              "data": {
                                "id": 1,
                                "email": "a.dudakov@reqres.in",
                                "first_name": "Aleksey",
                                "last_name": "Dudakov",
                                "avatar": "https://reqres.in/img/faces/1-image.jpg"
                              }
                            }
                        """.trimIndent()
                        )
                )
        )

        webTestClient.get()
            .uri("/web-users/1")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody()
            .jsonPath("$.id").isEqualTo("1")
            .jsonPath("$.username").isEqualTo("Aleksey Dudakov")
    }

    @Test
    fun `should return error when called`(applicationContext: ConfigurableApplicationContext) {
        wireMockServer.stubFor(
            WireMock.get("/api/users/1")
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                )
        )

        webTestClient.get()
            .uri("/web-users/1")
            .exchange()
            .expectStatus().is5xxServerError
    }

    companion object {

        @JvmStatic
        @RegisterExtension
        private val wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build()

        @JvmStatic
        @DynamicPropertySource
        private fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("clients.web-user.base-url", wireMockServer::baseUrl)
        }
    }
}