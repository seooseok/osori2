package com.osori.cave.domain.navigation.controller

import com.osori.cave.domain.navigation.NavigationTreeService
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/navigation-tree")
class NavigationTreeController
@Autowired constructor(private val navigationTreeService: NavigationTreeService) {

    @GetMapping("/nodes")
    fun findAllNodes(): Resources<NodeResource> {
        val resources = navigationTreeService.findNodes().map { it.toResource() }

        resources.forEach { it.add(linkTo(methodOn(this::class.java).findNode(3)).withSelfRel()) }

        return Resources(resources, linkTo(methodOn(this::class.java).findAllNodes()).withSelfRel())
    }

    @PostMapping("/nodes")
    fun addNodes(@RequestBody @Valid nodes: List<NodeResource>): Resources<NodeResource> {
        val resources = mutableListOf<NodeResource>()

        nodes.forEach {
            val id = navigationTreeService.create(it.name, it.resource, it.depthType, it.methodType, it.parentId)
            val resource = navigationTreeService.findNode(id).toResource()
            resource.add(linkTo(methodOn(this::class.java).findNode(id)).withSelfRel())
            resources.add(resource)
        }

        return Resources(resources, linkTo(methodOn(this::class.java).findAllNodes()).withSelfRel())
    }


    @GetMapping("/node/{id}")
    fun findNode(@PathVariable id: Long): Resource<NodeResource> {
        val uriPart = navigationTreeService.findNode(id)
        return Resource(uriPart.toResource(), linkTo(methodOn(this::class.java).findNode(id)).withSelfRel())
    }

    @PostMapping("/node")
    fun addNode(@RequestBody node: NodeResource): Resource<Long> {

        val id = navigationTreeService.create(node.name, node.resource, node.depthType, node.methodType, node.parentId)
        return Resource(id, linkTo(methodOn(this::class.java).findNode(id)).withRel("node"))
    }

    @PutMapping("/node/{id}")
    fun modifyChildren(@PathVariable id: Long, menuNode: NodeResource) {
        menuNode.apply { this.id = id }
    }

    @DeleteMapping("/node/{id}")
    fun removeChildren(@PathVariable id: Long) {
        navigationTreeService.removeNode(id)
    }
}
