package com.osori.cave.domain.permission.controller

import com.osori.cave.domain.permission.PermissionService
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/permission")
class PermissionController
@Autowired constructor(private val permissionService: PermissionService) {

    @GetMapping("/groups")
    fun findAllGroups(): Resources<PermissionResource> {
        val permissions = permissionService.findAll().map { it.toResource() }

        permissions.forEach {
            it.add(ControllerLinkBuilder
                    .linkTo(ControllerLinkBuilder.methodOn(this::class.java).findGroup(it.id!!))
                    .withSelfRel())
        }

        return Resources(permissions, ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(this::class.java).findAllGroups())
                .withSelfRel())
    }

    @GetMapping("/group")
    fun findGroup(@PathVariable id: Long): Resource<PermissionResource> {
        val permission = permissionService.findOne(id)

        return Resource(permission.toResource(), ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(this::class.java).findGroup(id))
                .withSelfRel())
    }

    @PostMapping("/group")
    fun createGroup(@RequestBody @Valid resource: PermissionResource): Resource<Long> {

        val id = permissionService.create(resource.name, resource.menuNodes.map { it.id!! })

        return Resource(id, ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(this::class.java).findGroup(id))
                .withRel("group"))
    }
}
