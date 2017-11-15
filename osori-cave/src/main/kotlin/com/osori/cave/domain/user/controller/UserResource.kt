package com.osori.cave.domain.user.controller

import com.fasterxml.jackson.annotation.JsonView
import com.osori.cave.domain.navigation.controller.MenuNodeResource
import com.osori.cave.domain.permission.controller.PermissionResource
import org.springframework.hateoas.ResourceSupport


data class UserResource(var loginId: String) : ResourceSupport() {
    @JsonView(UserView.Base::class)
    var id: Long? = null
    var name: String? = null

    @JsonView(UserView.Detail::class)
    var email: String? = null
    @JsonView(UserView.Detail::class)
    var phone: String? = null
    @JsonView(UserView.Detail::class)
    var position: String? = null
    @JsonView(UserView.Detail::class)
    var department: String? = null

    var created: String? = null
    var status: String? = null

    @JsonView(UserView.Permission::class)
    var permissionGrants = listOf<PermissionResource>()
    @JsonView(UserView.Permission::class)
    var personalGrants = listOf<MenuNodeResource>()
}


interface UserView {
    interface Base
    interface Detail : Base
    interface Permission : Base
}
