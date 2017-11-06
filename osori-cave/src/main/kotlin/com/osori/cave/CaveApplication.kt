package com.osori.cave

import com.osori.cave.user.PersonalInformation
import com.osori.cave.user.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories
@SpringBootApplication
class CaveApplication {

    @Bean
    fun init(userService: UserService): CommandLineRunner = CommandLineRunner {
        listOf("Doomfist", "Diva", "Genji", "Mccree", "pharah", "reaper", "soldier", "sombra").map { name ->
            userService.create(name + "-id", name, PersonalInformation("$name@gmail.com", phone = "01012341234"))
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(CaveApplication::class.java, *args)
}


