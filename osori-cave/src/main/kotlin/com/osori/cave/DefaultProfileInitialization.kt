package com.osori.cave

import com.osori.cave.domain.navigation.MenuTreeService
import com.osori.cave.domain.navigation.controller.MenuNodeResource
import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.user.PersonalInformation
import com.osori.cave.domain.user.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.RequestMethod

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
    fun initNavigations(menuTreeService: MenuTreeService): CommandLineRunner = CommandLineRunner {

        val rootId = menuTreeService.create(MenuNodeResource("Home", "", UriPart.DepthType.NAVI, RequestMethod.GET))
        val accountId = menuTreeService.create(MenuNodeResource("Account", "/account", UriPart.DepthType.NAVI, RequestMethod.GET).apply { parentId = rootId })
        menuTreeService.create(MenuNodeResource("Show Users", "/users", UriPart.DepthType.FUNC, RequestMethod.GET).apply { parentId = accountId })
        menuTreeService.create(MenuNodeResource("Show User Info", "/user/{id}", UriPart.DepthType.FUNC, RequestMethod.GET).apply { parentId = accountId })
        menuTreeService.create(MenuNodeResource("Modify User", "/user/{id}", UriPart.DepthType.FUNC, RequestMethod.PUT).apply { parentId = accountId })
        menuTreeService.create(MenuNodeResource("Expire User", "/user/{id}", UriPart.DepthType.FUNC, RequestMethod.DELETE).apply { parentId = accountId })
        val navigationId = menuTreeService.create(MenuNodeResource("Navigation", "/navigation", UriPart.DepthType.NAVI, RequestMethod.GET).apply { parentId = rootId })
        menuTreeService.create(MenuNodeResource("Show Url Tree", "/nodes", UriPart.DepthType.FUNC, RequestMethod.GET).apply { parentId = navigationId })
    }

}
