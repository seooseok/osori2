package com.osori.cave.service

import com.osori.cave.model.UriPart
import com.osori.cave.model.User
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
    }

    fun findNodes():List<MenuNode>{
        val uriParts = repository.findByStatusTrue()
        return uriParts.map { u -> u.toResource()}
    }

    fun findNodes(user:User):List<MenuNode> {
        return user.getUriParts().map { u -> u.toResource() }
    }

    fun moveNode(nodeId: Long, parentNodeId:Long){
        UriPartManager(repository,nodeId)
                .moveTo(parentNodeId)
                .save()
    }

    fun resetTree(){
        val rootId = repository.findByParentIdIsNull().id?: throw IllegalStateException("root is empty")
        UriPartManager(repository, rootId)
                .orphanRemove()
                .save()
    }


    private fun UriPart.toResource(): MenuNode {
        val menuNode = MenuNode(this.name,this.resource,this.depthType,this.methodType)
        menuNode.id = this.id
        menuNode.fullUri = getFullUri(this)
        return menuNode
    }

    private fun getFullUri(uriPart: UriPart):String? {
        return when {
            uriPart.parentUriPart == null -> uriPart.resource
            else -> getFullUri(uriPart.parentUriPart!!) + uriPart.resource
        }
    }

}
