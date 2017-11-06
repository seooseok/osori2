package com.osori.cave

import com.osori.cave.user.infrastructure.User
import com.osori.cave.user.infrastructure.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories
@SpringBootApplication
class CaveApplication {

    @Bean
    fun init(userRepository: UserRepository): CommandLineRunner = CommandLineRunner {
        listOf("Doomfist", "Diva", "Genji", "Mccree", "pharah", "reaper", "soldier", "sombra").map { name ->
            userRepository.save(User(name + "-id", name))
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(CaveApplication::class.java, *args)
}


