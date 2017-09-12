package com.osori.cave

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class CaveApplication

fun main(args: Array<String>) {
    SpringApplication.run(CaveApplication::class.java, *args)
}
