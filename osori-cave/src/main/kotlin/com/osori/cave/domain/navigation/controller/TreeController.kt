package com.osori.cave.domain.navigation.controller

import com.osori.cave.domain.navigation.MenuTreeService
import com.osori.cave.domain.navigation.infrastructure.UriPart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/navigation-trees")
class TreeController
@Autowired constructor(private val menuTreeService: MenuTreeService) {

    @GetMapping("")
    fun findAllNodes(): List<MenuNodeResource> {
        return menuTreeService.findNodes()
    }

    @PostMapping("/children")
    fun addChildren(privilegeUrl: PrivilegeUrlResource) {
        privilegeUrl.validate()
        val depthType = UriPart.DepthType.valueOf(privilegeUrl.depthType)

        menuTreeService.create(MenuNodeResource(privilegeUrl.getTitle, privilegeUrl.uriPart, depthType, GET))

        privilegeUrl.postTitle?.let {
            menuTreeService.create(MenuNodeResource(privilegeUrl.postTitle!!, privilegeUrl.uriPart, depthType, POST))
        }

        privilegeUrl.putTitle?.let {
            menuTreeService.create(MenuNodeResource(privilegeUrl.putTitle!!, privilegeUrl.uriPart, depthType, PUT))
        }

        privilegeUrl.deleteTitle?.let {
            menuTreeService.create(MenuNodeResource(privilegeUrl.deleteTitle!!, privilegeUrl.uriPart, depthType, DELETE))
        }
    }

    @PutMapping("/children/{id}")
    fun modifyChildren(@PathVariable id: Long, menuNode: MenuNodeResource) {
        menuNode.apply { this.id = id }
        menuTreeService.modifyNode(menuNode)
    }

    @DeleteMapping("/children/{id}")
    fun removeChildren(@PathVariable id: Long) {
        menuTreeService.removeNode(id)
    }
}

data class PrivilegeUrlResource(val depthType: String,
                                val uriPart: String,
                                var getTitle: String,
                                var postTitle: String? = null,
                                var putTitle: String? = null,
                                var deleteTitle: String? = null)

private fun PrivilegeUrlResource.validate() {
    if (this.depthType.isEmpty()) throw IllegalArgumentException("privilege type is empty")
    if (this.uriPart.isEmpty()) throw IllegalArgumentException("uri is empty")
    if (this.getTitle.isEmpty()) throw IllegalArgumentException("GET api title is empty")
}
