package com.osori.cave.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


class Jackson {
    companion object {
        fun getMapper(): ObjectMapper {
            return Jackson2ObjectMapperBuilder.json()
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .build<ObjectMapper>()
        }
    }
}
