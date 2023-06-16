package com.dddeurope.recycle

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


fun main(args: Array<String>) = runApplication<RecycleJavaApplication>(*args).let { }

@SpringBootApplication
class RecycleJavaApplication {
    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().apply {
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        registerModule(JavaTimeModule())
        registerKotlinModule()
    }
}

