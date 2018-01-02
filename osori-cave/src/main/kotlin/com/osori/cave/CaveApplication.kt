package com.osori.cave

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class CaveApplication

fun main(args: Array<String>) {
    SpringApplication.run(CaveApplication::class.java, *args)
}
