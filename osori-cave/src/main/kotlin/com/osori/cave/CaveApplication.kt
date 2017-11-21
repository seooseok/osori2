package com.osori.cave

import com.osori.cave.domain.navigation.MenuTreeService
import com.osori.cave.domain.navigation.controller.MenuNodeResource
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.FUNC
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.NAVI
import com.osori.cave.domain.user.PersonalInformation
import com.osori.cave.domain.user.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.PUT


@EnableJpaRepositories
@SpringBootApplication
class CaveApplication {

    @Bean
    fun initUsers(userService: UserService): CommandLineRunner = CommandLineRunner {
        listOf("Doomfist", "Diva", "Genji", "Mccree", "pharah", "reaper", "soldier", "sombra").map { name ->
            userService.create(name + "-id", name, PersonalInformation("$name@gmail.com", phone = "01012341234"))
        }
    }

    @Bean
    fun initNavi(menuTreeService: MenuTreeService): CommandLineRunner = CommandLineRunner {

        val rootId = menuTreeService.create(MenuNodeResource("Home", "", NAVI, GET))
        val accountId = menuTreeService.create(MenuNodeResource("Account", "/account", NAVI, GET).apply { parentId = rootId })
        menuTreeService.create(MenuNodeResource("Show Users", "/users", FUNC, GET).apply { parentId = accountId })
        menuTreeService.create(MenuNodeResource("Show User Info", "/user/{id}", FUNC, GET).apply { parentId = accountId })
        menuTreeService.create(MenuNodeResource("Modify User", "/user/{id}", FUNC, PUT).apply { parentId = accountId })
        menuTreeService.create(MenuNodeResource("Expire User", "/user/{id}", FUNC, DELETE).apply { parentId = accountId })
        val navigationId = menuTreeService.create(MenuNodeResource("Navigation", "/navigation", NAVI, GET))
        menuTreeService.create(MenuNodeResource("Show Url Tree", "/nodes", FUNC, GET).apply { parentId = navigationId })
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(CaveApplication::class.java, *args)
}


