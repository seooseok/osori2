package com.osori.cave.user.controller

import com.osori.cave.navigation.controller.MenuNodeResource
import com.osori.cave.permission.controller.PermissionResource


data class UserResource(var loginId: String) {
    var id: Long? = null
    var name: String? = null

    var email: String? = null
    var phone: String? = null
    var position: String? = null
    var department: String? = null

    var created: String? = null
    var status: String? = null

    var permissionGrants = listOf<PermissionResource>()
    var personalGrants = listOf<MenuNodeResource>()
}
