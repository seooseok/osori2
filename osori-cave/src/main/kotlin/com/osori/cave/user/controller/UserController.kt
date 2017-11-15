package com.osori.cave.user.controller

import com.fasterxml.jackson.annotation.JsonView
import com.osori.cave.user.PersonalInformation
import com.osori.cave.user.UserSearchCondition
import com.osori.cave.user.UserService
import com.osori.cave.user.infrastructure.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/account")
class UserController
@Autowired constructor(private val userService: UserService) {


    @JsonView(UserView.Base::class)
    @GetMapping("/users")
    fun search(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam startDate: LocalDate,
               @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam endDate: LocalDate,
               @RequestParam(required = false) loginId: String? = null,
               @RequestParam(required = false) name: String? = null,
               @RequestParam(required = false) status: String? = null): Resources<UserResource> {

        var enumStatus: User.Status? = null

        status?.let { enumStatus = User.Status.valueOf(it.toUpperCase()) }

        val resources = userService.findUsers(UserSearchCondition(startDate, endDate, loginId, name, enumStatus))

        resources.forEach { it.add(linkTo(methodOn(this::class.java).findOneWithDetail(it.id!!)).withRel("detail")) }

        return Resources(resources, linkTo(methodOn(this::class.java).search(startDate, endDate, loginId, name, status)).withSelfRel())
    }

    @JsonView(UserView.Detail::class)
    @GetMapping("/user/{id}")
    fun findOneWithDetail(@PathVariable id: Long): Resource<UserResource> {
        val resource = userService.findOne(id)
        return Resource(resource, linkTo(methodOn(this::class.java).findOneWithDetail(id)).withSelfRel())
    }

    @PutMapping("/user/{id}")
    fun modify(@PathVariable id: Long,
               @RequestParam name: String,
               @RequestParam status: String,
               @RequestParam(required = false) email: String?,
               @RequestParam(required = false) phone: String?,
               @RequestParam(required = false) position: String?,
               @RequestParam(required = false) department: String?,
               @RequestParam(required = false) comment: String?): Resource<String> {

        val information = PersonalInformation(email, phone, position, department, comment)

        userService.modify(id, name, information, User.Status.valueOf(status))

        return Resource("success", linkTo(methodOn(this::class.java).findOneWithDetail(id)).withSelfRel())
    }

    @DeleteMapping("/user/{id}")
    fun remove(@PathVariable id: Long): Resource<String> {
        userService.expireUser(id)

        return Resource("success", linkTo(methodOn(this::class.java)
                .search(LocalDate.now().minusWeeks(1), LocalDate.now())).withRel("before"))
    }

}
