package com.osori.cave.nodetree.controller

import com.osori.cave.nodetree.infrastructure.UriPart
import org.springframework.web.bind.annotation.RequestMethod


data class MenuNodeResource(var name:String,
                            var resource:String,
                            var depthType: UriPart.DepthType,
                            var methodType: RequestMethod){
    var id:Long? = null
    var parentId:Long? = null
    var fullUri:String? = null
    var num:Int = 0
}
