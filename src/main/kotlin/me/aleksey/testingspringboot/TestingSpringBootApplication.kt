package me.aleksey.testingspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestingSpringBootApplication

fun main(args: Array<String>) {
    runApplication<TestingSpringBootApplication>(*args)
}
