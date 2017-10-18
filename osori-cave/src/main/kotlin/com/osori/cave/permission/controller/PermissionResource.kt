package com.osori.cave.permission.controller

import com.osori.cave.navigation.controller.MenuNodeResource


data class PermissionResource(var name: String) {
    var id: Long? = null
    var menuNodes: List<MenuNodeResource>? = null
}
