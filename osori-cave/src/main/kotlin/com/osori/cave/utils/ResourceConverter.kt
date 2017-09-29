package com.osori.cave.utils

import com.osori.cave.nodetree.controller.MenuNodeResource
import com.osori.cave.nodetree.infrastructure.UriPart
import com.osori.cave.permission.controller.PermissionResource
import com.osori.cave.permission.infrastructure.Permission


fun UriPart.toResource(): MenuNodeResource {
    val menuNode = MenuNodeResource(this.name, this.resource, this.depthType, this.methodType)
    menuNode.id = this.id
    menuNode.num = this.num
    menuNode.fullUri = getFullUri(this)
    if(this.parentUriPart != null)
        menuNode.parentId = this.parentUriPart!!.id
    return menuNode
}

private fun getFullUri(uriPart: UriPart):String? {
    return when {
        uriPart.parentUriPart == null -> uriPart.resource
        else -> getFullUri(uriPart.parentUriPart!!) + uriPart.resource
    }
}

fun Permission.toResource(): PermissionResource {
    val resource = PermissionResource(this.name)
    resource.id = this.id
    if(!this.getUriParts().isEmpty()){
        resource.menuNodes = this.getUriParts().map { u -> u.toResource() }
    }

    return resource
}
