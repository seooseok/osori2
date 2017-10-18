package com.osori.cave.navigation.controller

import com.osori.cave.navigation.infrastructure.UriPart
import org.springframework.web.bind.annotation.RequestMethod


data class MenuNodeResource(
        var name:String,
        var resource:String,
        var depthType: UriPart.DepthType,
        var methodType: RequestMethod
){
    var id:Long? = null
    var parentId:Long? = null

    var viewId:Long? = null
    var viewParentId:Long? = null

    var fullUri:String? = null
    var sorting:Int = 0
}
