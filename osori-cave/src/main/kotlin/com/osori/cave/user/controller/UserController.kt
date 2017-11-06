package com.osori.cave.user.controller

import com.osori.cave.user.UserSearchCondition
import com.osori.cave.user.UserService
import com.osori.cave.user.infrastructure.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/account")
class UserController
@Autowired constructor(private val userService: UserService) {


    @GetMapping("/users")
    fun search(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam startDate: LocalDate,
               @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam endDate: LocalDate,
               loginId: String? = null,
               name: String? = null,
               status: String? = null): List<UserResource> {

        var enumStatus: User.Status? = null

        status?.let { enumStatus = User.Status.valueOf(it.toUpperCase()) }

        return userService.findUsers(UserSearchCondition(startDate, endDate, loginId, name, enumStatus))
    }
}
