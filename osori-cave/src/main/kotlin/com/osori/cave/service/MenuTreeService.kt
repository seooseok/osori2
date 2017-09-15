package com.osori.cave.service

import com.osori.cave.model.UriPart
import com.osori.cave.repository.UriPartRepository
import com.osori.cave.resource.MenuNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuTreeService
@Autowired constructor(private val repository: UriPartRepository) {

    fun findNode(nodeId:Long): MenuNode{
        val uriPart = repository.findOne(nodeId)

        return uriPart.toResource()
                .apply {
                    fullUri = uriPart.parentUriPart?.let { p -> p.resource + uriPart.resource }
                }
    }


    private fun UriPart.toResource(): MenuNode {
        return MenuNode(this.name,this.resource,this.depthType,this.methodType)
                .apply {
                    id = this.id
                }
    }

}
