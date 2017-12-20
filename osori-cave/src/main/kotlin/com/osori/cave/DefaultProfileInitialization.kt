package com.osori.cave

import com.osori.cave.domain.navigation.NavigationTreeService
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.FUNC
import com.osori.cave.domain.navigation.infrastructure.UriPart.DepthType.NAVI
import com.osori.cave.domain.user.PersonalInformation
import com.osori.cave.domain.user.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.PUT

@Configuration
class DefaultProfileInitialization {

    @Profile("default")
    @Bean
    fun initUsers(userService: UserService): CommandLineRunner = CommandLineRunner {
        listOf("Doomfist", "Diva", "Genji", "Mccree", "pharah", "reaper", "soldier", "sombra").map { name ->
            userService.create(name + "-id", name, PersonalInformation("$name@gmail.com", phone = "01012341234"))
        }
    }

    @Profile("default")
    @Bean
    fun initNavigations(navigationTreeService: NavigationTreeService): CommandLineRunner = CommandLineRunner {

        val rootId = navigationTreeService.create("Home", "", NAVI, GET)
        val accountId = navigationTreeService.create("Account", "/account", NAVI, GET, rootId)
        navigationTreeService.create("Show Users", "/users", FUNC, GET, accountId)
        navigationTreeService.create("Show User Info", "/user/{id}", FUNC, GET, accountId)
        navigationTreeService.create("Modify User", "/user/{id}", FUNC, PUT, accountId)
        navigationTreeService.create("Expire User", "/user/{id}", FUNC, DELETE, accountId)
        val navigationId = navigationTreeService.create("Navigation", "/navigation", NAVI, GET, rootId)
        navigationTreeService.create("Show Url Tree", "/nodes", FUNC, GET, navigationId)
    }
}
