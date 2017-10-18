package com.osori.cave.utils

import com.osori.cave.navigation.controller.MenuNodeResource
import com.osori.cave.navigation.infrastructure.UriPart
import com.osori.cave.permission.controller.PermissionResource
import com.osori.cave.permission.infrastructure.Permission
import com.osori.cave.user.PersonalInformation
import com.osori.cave.user.controller.UserResource
import com.osori.cave.user.infrastructure.User


fun UriPart.toResource(): MenuNodeResource {
    val menuNode = MenuNodeResource(this.name, this.resource, this.depthType, this.methodType)
    menuNode.id = this.id
    menuNode.sorting = this.sorting
    menuNode.viewId = this.viewId
    menuNode.viewParentId = this.viewParentId

    menuNode.fullUri = getFullUri(this)
    if (this.parentUriPart != null)
        menuNode.parentId = this.parentUriPart!!.id
    return menuNode
}

private fun getFullUri(uriPart: UriPart): String? {
    return when {
        uriPart.parentUriPart == null -> uriPart.resource
        else -> getFullUri(uriPart.parentUriPart!!) + uriPart.resource
    }
}

fun Permission.toResource(): PermissionResource {
    val resource = PermissionResource(this.name)
    resource.id = this.id
    if (this.getUriParts().isNotEmpty()) {
        resource.menuNodes = this.getUriParts().map(UriPart::toResource)
    }

    return resource
}

fun User.toResource(personalInformation: PersonalInformation? = null): UserResource {
    val user = UserResource(this.loginId)
    user.id = this.id
    user.name = this.name
    if (this.getPermissions().isNotEmpty()) {
        user.permissionGrants = this.getPermissions().map(Permission::toResource)
    }
    if (this.getUriParts().isNotEmpty()) {
        user.personalGrants = this.getUriParts().map(UriPart::toResource)
    }
    personalInformation?.let {
        user.email = it.email
        user.phone = it.phone
        user.department = it.department
        user.position = it.position
    }

    return user
}


