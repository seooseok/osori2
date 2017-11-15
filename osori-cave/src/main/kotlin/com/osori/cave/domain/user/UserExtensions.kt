package com.osori.cave.domain.user

import com.osori.cave.domain.navigation.infrastructure.UriPart
import com.osori.cave.domain.permission.infrastructure.Permission
import com.osori.cave.domain.user.controller.UserResource
import com.osori.cave.domain.user.infrastructure.User
import com.osori.cave.utils.toResource


fun User.toResource(): UserResource {
    val user = UserResource(this.loginId)
    user.id = this.id
    user.name = this.name
    user.status = this.status.toString()
    user.created = this.getCreated()
    return user
}

fun User.toResource(personalInformation: PersonalInformation? = null): UserResource {
    val user = this.toResource()

    personalInformation?.let {
        user.email = it.email
        user.phone = it.phone
        user.department = it.department
        user.position = it.position
    }

    return user
}

fun User.toResourceHasPermission(): UserResource {
    val user = this.toResource()

    if (this.getPermissions().isNotEmpty()) {
        user.permissionGrants = this.getPermissions().map(Permission::toResource)
    }
    if (this.getUriParts().isNotEmpty()) {
        user.personalGrants = this.getUriParts().map(UriPart::toResource)
    }
    return user
}
