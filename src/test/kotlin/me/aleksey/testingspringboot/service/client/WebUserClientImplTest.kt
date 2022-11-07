package me.aleksey.testingspringboot.service.client

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

internal class WebUserClientImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var webUserClient: WebUserClient

    @BeforeEach
    fun init() {
        mockWebServer = MockWebServer().apply {
            start()
            webUserClient = WebUserClientImpl(url("/").toString())
        }
    }

    @Test
    fun `should return web user when called`() {
        val mockResponse = MockResponse()
            .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setResponseCode(HttpStatus.OK.value())
            .setBody(
                """
                {
                    "data": {
                        "id": 1,
                        "first_name": "Aleksey",
                        "last_name": "Dudakov"
                    }
                }
            """.trimIndent()
            )
        mockWebServer.enqueue(mockResponse)

        val result = webUserClient.getWebUser(1)

        assertThat(result).isNotNull
        assertThat(result!!.data.id).isEqualTo(1)
        assertThat(result.data.firstName).isEqualTo("Aleksey")
        assertThat(result.data.lastName).isEqualTo("Dudakov")
    }

    @Test
    fun `should fail on error when return user`() {
        val mockResponse = MockResponse()
            .setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        mockWebServer.enqueue(mockResponse)

        assertThatThrownBy { webUserClient.getWebUser(1) }
    }
}