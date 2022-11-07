package me.aleksey.testingspringboot.listener

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.stereotype.Component

@Component
class SimpleListener {

    @KafkaListener(
        topics = ["quickstart"],
        properties = [ "${JsonDeserializer.VALUE_DEFAULT_TYPE}=me.aleksey.testingspringboot.listener.Message" ]
    )
    fun process(message: Message) {
        println(message)
    }
}

data class Message(
    val msg: String,
    val value: Int
)