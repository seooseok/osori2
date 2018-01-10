package com.osori.cave.domain.user.controller

import com.fasterxml.jackson.annotation.JsonView
import com.osori.cave.domain.navigation.controller.NodeResource
import com.osori.cave.domain.permission.controller.PermissionResource
import org.springframework.hateoas.ResourceSupport

data class UserResource(var loginId: String) : ResourceSupport() {
    @JsonView(UserView.Users::class)
    var id: Long? = null
    var name: String? = null

    @JsonView(UserView.User::class)
    var email: String? = null
    @JsonView(UserView.User::class)
    var phone: String? = null
    @JsonView(UserView.User::class)
    var position: String? = null
    @JsonView(UserView.User::class)
    var department: String? = null
    @JsonView(UserView.User::class)
    var comment: String? = null

    var created: String? = null
    var status: String? = null

    @JsonView(UserView.Detail::class)
    var permissionGrants = listOf<PermissionResource>()
    @JsonView(UserView.Detail::class)
    var personalGrants = listOf<NodeResource>()
}

interface UserView {
    interface Users
    interface User : Users
    interface Detail : Users
}
