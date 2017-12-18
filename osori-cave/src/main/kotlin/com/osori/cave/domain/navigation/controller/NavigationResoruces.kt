package com.osori.cave.domain.navigation.controller

import com.osori.cave.domain.navigation.infrastructure.UriPart
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.hateoas.ResourceSupport
import org.springframework.web.bind.annotation.RequestMethod


data class MenuNodeResource(
        var name: String,
        var resource: String,
        var depthType: UriPart.DepthType,
        var methodType: RequestMethod
) : ResourceSupport() {
    var id: Long? = null
    var parentId: Long? = null

    var fullUri: String? = null
}

data class PrivilegeUrlResource(
        @NotEmpty
        val depthType: String,
        @NotEmpty
        val uriPart: String,

        var getTitle: String? = null,
        var postTitle: String? = null,
        var putTitle: String? = null,
        var deleteTitle: String? = null
)
