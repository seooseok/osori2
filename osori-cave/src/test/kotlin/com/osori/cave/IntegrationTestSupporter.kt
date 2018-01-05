package com.osori.cave

import com.osori.cave.domain.navigation.NavigationTreeService
import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.user.PersonalInformation
import com.osori.cave.domain.user.UserService
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMethod

@Transactional
@ExtendWith(SpringExtension::class)
@ComponentScan
@SpringBootTest(classes = arrayOf(CaveApplication::class))
class IntegrationTestSupporter {
    @Bean
    fun initUsers(userService: UserService): CommandLineRunner = CommandLineRunner {
        listOf("Doomfist", "Diva", "Genji", "Mccree", "pharah", "reaper", "soldier", "sombra").map { name ->
            userService.create(name + "-id", name, PersonalInformation("$name@gmail.com", phone = "01012341234"))
        }
    }

    @Bean
    fun initNavigations(navigationTreeService: NavigationTreeService): CommandLineRunner = CommandLineRunner {

        val rootId = navigationTreeService.create("Home", "", UriPart.DepthType.NAVI, RequestMethod.GET)
        val accountId = navigationTreeService.create("Account", "/account", UriPart.DepthType.NAVI, RequestMethod.GET, rootId)
        navigationTreeService.create("Show Users", "/users", UriPart.DepthType.FUNC, RequestMethod.GET, accountId)
        navigationTreeService.create("Show User Info", "/user/{id}", UriPart.DepthType.FUNC, RequestMethod.GET, accountId)
        navigationTreeService.create("Modify User", "/user/{id}", UriPart.DepthType.FUNC, RequestMethod.PUT, accountId)
        navigationTreeService.create("Expire User", "/user/{id}", UriPart.DepthType.FUNC, RequestMethod.DELETE, accountId)
        val navigationId = navigationTreeService.create("Navigation", "/navigation", UriPart.DepthType.NAVI, RequestMethod.GET, rootId)
        navigationTreeService.create("Show Url Tree", "/nodes", UriPart.DepthType.FUNC, RequestMethod.GET, navigationId)
    }

}
