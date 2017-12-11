package com.osori.cave.domain.navigation.controller

import com.osori.cave.domain.navigation.infrastructure.UriPart
import org.springframework.web.bind.annotation.RequestMethod


data class MenuNodeResource(
        var name: String,
        var resource: String,
        var depthType: UriPart.DepthType,
        var methodType: RequestMethod
) {
    var id: Long? = null
    var parentId: Long? = null

    var fullUri: String? = null
}
