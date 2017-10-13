package com.osori.cave.nodetree

import com.osori.cave.nodetree.controller.MenuNodeResource
import com.osori.cave.nodetree.infrastructure.UriPart
import com.osori.cave.nodetree.infrastructure.UriPartRepository
import com.osori.cave.user.infrastructure.User
import com.osori.cave.utils.toResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MenuTreeService
@Autowired constructor(private val repository: UriPartRepository) {

    fun create(menuNodeResource: MenuNodeResource) {
        val uriPart = UriPart(menuNodeResource.name, menuNodeResource.resource, menuNodeResource.depthType, menuNodeResource.methodType).apply {
            num = menuNodeResource.num
        }

        if (menuNodeResource.parentId != null) {
            val parentUriPart = repository.findOne(menuNodeResource.parentId)
            uriPart.setByParent(parentUriPart)
        }
        save(uriPart)
    }

    fun findNode(nodeId:Long): MenuNodeResource {
        val uriPart = repository.findOne(nodeId)
        return uriPart.toResource()
    }

    fun findNodes():List<MenuNodeResource>{
        val uriParts = repository.findByStatusTrue()
        return uriParts.map { u -> u.toResource()}
    }

    fun findNodes(user: User):List<MenuNodeResource> {
        return user.getUriParts().map { u -> u.toResource() }
    }

    fun moveNode(nodeId: Long, parentNodeId:Long){
        val uriPart = repository.findOne(nodeId)
        val parentUriPart = repository.findOne(parentNodeId)
        uriPart.setByParent(parentUriPart)
    }

    fun modifyNode(menuNodeResource: MenuNodeResource){
        val uriPart = repository.findOne(menuNodeResource.id).apply {
            name = menuNodeResource.name
            resource = menuNodeResource.resource
            methodType = menuNodeResource.methodType
            depthType = menuNodeResource.depthType
            num = menuNodeResource.num
        }
    }

    fun removeNode(nodeId: Long){
        val uriPart = repository.findOne(nodeId)
        if(uriPart.parentUriPart == null)
            throw IllegalStateException("${nodeId} is root node. root node. can't remove")
        uriPart.status = false
    }

    fun resetTree(){
        val rootUriPart = repository.findByParentUriPartIsNull()?: throw IllegalStateException("can't reset tree")
        rootUriPart.uriParts.map { u -> orphanRemove(u) }
    }

    private fun save(uriPart: UriPart) = repository.save(uriPart)

    private fun orphanRemove(uriPart: UriPart){
        uriPart.status = false
        if(uriPart.uriParts.isEmpty().not()){
            uriPart.uriParts.map { u -> orphanRemove(u) }
        }
    }

}
