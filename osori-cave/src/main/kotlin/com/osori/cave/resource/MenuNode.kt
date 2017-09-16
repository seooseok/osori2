package com.osori.cave.resource

import com.osori.cave.model.UriPart
import org.springframework.web.bind.annotation.RequestMethod


data class MenuNode(var name:String,
                    var resource:String,
                    var depthType: UriPart.DepthType,
                    var methodType: RequestMethod){
    var id:Long? = null
    var parentId:Long? = null
    var fullUri:String? = null
}
