package com.osori.cave.domain.permission.controller

import com.osori.cave.domain.navigation.controller.NodeResource
import org.springframework.hateoas.ResourceSupport

data class PermissionResource(var name: String) : ResourceSupport() {
    var id: Long? = null
    var menuNodes: List<NodeResource> = listOf()
}
