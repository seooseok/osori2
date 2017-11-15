package com.osori.cave.domain.permission.controller

import com.osori.cave.domain.navigation.controller.MenuNodeResource


data class PermissionResource(var name: String) {
    var id: Long? = null
    var menuNodes: List<MenuNodeResource>? = null
}
