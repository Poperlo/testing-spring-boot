package me.aleksey.testingspringboot.controller

import me.aleksey.testingspringboot.listener.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/kafka")
class KafkaController(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @GetMapping
    fun publish() = kafkaTemplate.send("quickstart", Message("Hi", 10))
}
