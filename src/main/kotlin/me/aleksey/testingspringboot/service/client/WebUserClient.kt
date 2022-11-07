package me.aleksey.testingspringboot.service.client

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

interface WebUserClient {
    fun getWebUser(id: Int): WebUser?
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class WebUser(
    val data: Data
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Data(
        val id: Int,
        @JsonProperty("first_name")
        val firstName: String,
        @JsonProperty("last_name")
        val lastName: String
    )
}