package com.osori.cave.domain.navigation.controller

import com.fasterxml.jackson.annotation.JsonView
import com.osori.cave.domain.navigation.infrastructure.UriPart
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.hateoas.ResourceSupport
import org.springframework.web.bind.annotation.RequestMethod

data class NodeResource(
        @NotEmpty
        var name: String,
        @NotEmpty
        var resource: String,
        @NotEmpty
        var depthType: UriPart.DepthType,
        @NotEmpty
        var methodType: RequestMethod
) : ResourceSupport() {
    @JsonView
    var id: Long? = null

    var parentId: Long? = null

    var fullUri: String? = null
}
