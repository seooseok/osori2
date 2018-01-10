package com.osori.cave.utils

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


class Jackson {
    companion object {
        fun getMapper(): ObjectMapper {
            return Jackson2ObjectMapperBuilder.json()
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .modules(JavaTimeModule())
                    .build<ObjectMapper>()
                    .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true)
                    .registerKotlinModule()
        }
    }
}
