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

    fun create(menuNode: MenuNode) {
        val uriPart = UriPart(menuNode.name,menuNode.resource,menuNode.depthType,menuNode.methodType)
        if (menuNode.parentId != null) {
            val parentUriPart = repository.findOne(menuNode.parentId)
            uriPart.setByParent(parentUriPart)
        }
        save(uriPart)
    }

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
        val uriPart = repository.findOne(nodeId)
        val parentUriPart = repository.findOne(parentNodeId)
        uriPart.setByParent(parentUriPart)
        save(uriPart)
    }

    fun modifyNode(menuNode: MenuNode){
        val uriPart = repository.findOne(menuNode.id).apply {
            name = menuNode.name
            resource = menuNode.resource
            methodType = menuNode.methodType
            depthType = menuNode.depthType
        }
        save(uriPart)
    }

    fun removeNode(nodeId: Long){
        val uriPart = repository.findOne(nodeId)
        if(uriPart.parentUriPart == null)
            throw IllegalStateException("${nodeId} is root node. root node. can't remove")
        uriPart.status = false
        save(uriPart)
    }

    fun resetTree(){
        val rootUriPart = repository.findByParentUriPartIsNull()
        rootUriPart.uriParts.map { u -> orphanRemove(u) }
        save(rootUriPart)
    }

    private fun save(uriPart: UriPart){
        repository.save(uriPart)
    }

    private fun UriPart.toResource(): MenuNode {
        val menuNode = MenuNode(this.name,this.resource,this.depthType,this.methodType)
        menuNode.id = this.id
        menuNode.fullUri = getFullUri(this)
        if(this.parentUriPart != null)
            menuNode.parentId = this.parentUriPart!!.id
        return menuNode
    }

    private fun orphanRemove(uriPart: UriPart){
        uriPart.status = false
        if(uriPart.uriParts.isEmpty().not()){
            uriPart.uriParts.map { u -> orphanRemove(u) }
        }
    }

    private fun getFullUri(uriPart: UriPart):String? {
        return when {
            uriPart.parentUriPart == null -> uriPart.resource
            else -> getFullUri(uriPart.parentUriPart!!) + uriPart.resource
        }
    }

}
