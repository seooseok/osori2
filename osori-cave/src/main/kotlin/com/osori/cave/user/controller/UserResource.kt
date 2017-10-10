package com.osori.cave.user.controller

import com.osori.cave.nodetree.controller.MenuNodeResource
import com.osori.cave.permission.controller.PermissionResource


data class UserResource(var loginId:String) {
    var id:Long? = null
    var name:String? = null
    var permissionGrants= listOf<PermissionResource>()
    var personalGrants = listOf<MenuNodeResource>()
}
