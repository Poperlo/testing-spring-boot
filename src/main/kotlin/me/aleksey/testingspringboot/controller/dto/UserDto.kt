package me.aleksey.testingspringboot.controller.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto (
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val id: Int?,

    @JsonProperty("username")
    val name: String
)